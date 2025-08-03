package com.rahulyadav.shaadiaassignment.presentation.usermainscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulyadav.shaadiaassignment.core.wrapper.Resource
import com.rahulyadav.shaadiaassignment.domain.entity.MatchState
import com.rahulyadav.shaadiaassignment.domain.entity.User
import com.rahulyadav.shaadiaassignment.domain.repositoryinterface.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<List<User>>>(Resource.Loading)
    val state: StateFlow<Resource<List<User>>> = _state

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            _state.value = Resource.Loading
            val result = repository.getUserListData()
            _state.value = result
        }
    }


    fun onUserLiked(user: User) {
        viewModelScope.launch {
            repository.userAction(user.email, MatchState.ACCEPTED.value)
        }
    }

    fun onUserDisliked(user: User) {
        viewModelScope.launch {
            repository.userAction(user.email, MatchState.DECLINED.value)
        }
    }

}
