package com.rahulyadav.shaadiaassignment.presentation.usermainscreen

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahulyadav.shaadiaassignment.core.utils.gone
import com.rahulyadav.shaadiaassignment.core.utils.show
import com.rahulyadav.shaadiaassignment.core.wrapper.Resource
import com.rahulyadav.shaadiaassignment.databinding.ActivityUsersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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

        usersAdapter.onLikeClicked  = {user->
            viewModel.onUserLiked(user)
        }

        usersAdapter.onDislikeClicked = {user->
            viewModel.onUserDisliked(user)
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

                        usersAdapter.submitList(users)
                    }

                    is Resource.Error -> {
                        binding.loading.root.gone()
                    }
                }
            }
        }
    }
}