package com.rahulyadav.shaadiaassignment.presentation.usermainscreen

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahulyadav.shaadiaassignment.core.utils.gone
import com.rahulyadav.shaadiaassignment.core.utils.show
import com.rahulyadav.shaadiaassignment.core.wrapper.Resource
import com.rahulyadav.shaadiaassignment.databinding.ActivityUsersBinding
import com.rahulyadav.shaadiaassignment.domain.entity.MatchState
import com.rahulyadav.shaadiaassignment.work.MatchSyncWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class UsersActivity : FragmentActivity() {

    private lateinit var binding: ActivityUsersBinding

    @Inject
    lateinit var usersAdapter: UsersAdapter

    private val viewModel: UsersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()
        observeUsers()
    }

    private fun setUpViews() {
        binding.recyclerViewProfiles.apply {
            adapter = usersAdapter
            layoutManager = LinearLayoutManager(this@UsersActivity, LinearLayoutManager.VERTICAL, false)
        }

        usersAdapter.onLikeClicked  = {user, pos->
            viewModel.onUserLiked(user)
            usersAdapter.peek(pos)?.let {
                val updatedUser = it.copy(matchState = MatchState.ACCEPTED.value)
                val newList = usersAdapter.snapshot().items.toMutableList()
                newList[pos] = updatedUser
                usersAdapter.submitData(lifecycle, PagingData.from(newList))
            }
        }

        usersAdapter.onDislikeClicked = {user, pos->
            viewModel.onUserDisliked(user)
            usersAdapter.peek(pos)?.let {
                val updatedUser = it.copy(matchState = MatchState.DECLINED.value)
                val newList = usersAdapter.snapshot().items.toMutableList()
                newList[pos] = updatedUser
                usersAdapter.submitData(lifecycle, PagingData.from(newList))
            }
        }



    }

    private fun observeUsers() {
        lifecycleScope.launch {
            viewModel.state.collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                       binding.loading.root.show()
                        binding.loading.lottieLoading.playAnimation()
                    }

                    is Resource.Success -> {
                        binding.loading.root.gone()

                        val users = resource.data
                        usersAdapter.submitData(users)
                    }

                    is Resource.Error -> {
                        binding.loading.root.gone()
                    }
                }
            }
        }
    }
}