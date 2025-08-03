package com.rahulyadav.shaadiaassignment.presentation.usermainscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahulyadav.shaadiaassignment.core.utils.gone
import com.rahulyadav.shaadiaassignment.core.utils.loadImage
import com.rahulyadav.shaadiaassignment.core.utils.setMatchStateTextAndBackground
import com.rahulyadav.shaadiaassignment.core.utils.show
import com.rahulyadav.shaadiaassignment.core.utils.updateMatchStateVisibility
import com.rahulyadav.shaadiaassignment.databinding.UserItemLayoutBinding
import com.rahulyadav.shaadiaassignment.domain.entity.MatchState
import com.rahulyadav.shaadiaassignment.domain.entity.User
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject


@ActivityRetainedScoped
class UsersAdapter @Inject constructor() : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    private val users = mutableListOf<User>()

    var onLikeClicked: ((User) -> Unit)? = null
    var onDislikeClicked: ((User) -> Unit)? = null

    inner class UserViewHolder(val binding: UserItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        with(holder.binding) {
            textName.text = user.name
            textLocation.text = user.address
            imageProfile.loadImage(user.imageUrl)


            matchStatus.setMatchStateTextAndBackground(user.matchState)
            val isMatchStatusVisible = matchStatus.updateMatchStateVisibility(user.matchState)
            setVisibiltyOfButton(!isMatchStatusVisible)

            buttonLike.setOnClickListener {
                onLikeClicked?.invoke(user)
                setVisibiltyOfButton(false)
                matchStatus.setMatchStateTextAndBackground(MatchState.ACCEPTED.value)
                matchStatus.show()
            }

            buttonDislike.setOnClickListener {
                onDislikeClicked?.invoke(user)
                setVisibiltyOfButton(false)
                matchStatus.setMatchStateTextAndBackground(MatchState.DECLINED.value)
                matchStatus.show()
            }
        }
    }

    private fun UserItemLayoutBinding.setVisibiltyOfButton(visible: Boolean) {
        if (visible) {
            buttonLike.show()
            buttonDislike.show()
        } else {
            buttonLike.gone()
            buttonDislike.gone()
        }
    }

    override fun getItemCount(): Int = users.size

    fun submitList(newList: List<User>) {
        users.clear()
        users.addAll(newList)
        notifyDataSetChanged()
    }
}
