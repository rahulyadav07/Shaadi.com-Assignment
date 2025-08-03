package com.rahulyadav.shaadiaassignment.presentation.usermainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.rahulyadav.shaadiaassignment.core.wrapper.Resource
import com.rahulyadav.shaadiaassignment.domain.entity.MatchState
import com.rahulyadav.shaadiaassignment.domain.entity.User
import com.rahulyadav.shaadiaassignment.domain.usecase.GetUserListUseCase
import com.rahulyadav.shaadiaassignment.domain.usecase.MatchSyncUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    private val getMatchSyncUseCase: MatchSyncUseCase,
) : ViewModel() {


    private val _state = MutableStateFlow<Resource<PagingData<User>>>(Resource.Loading)
    val state: StateFlow<Resource<PagingData<User>>> = _state


    init {
        fetchPagedUsers()
    }

    private fun fetchPagedUsers() {
        viewModelScope.launch {
            getUserListUseCase()
                .collectLatest { result ->
                    _state.value = result
                }
        }
    }

    fun onUserLiked(user: User) {
        viewModelScope.launch {
            getMatchSyncUseCase(user.email, MatchState.ACCEPTED.value)
        }
    }

    fun onUserDisliked(user: User) {
        viewModelScope.launch {
            getMatchSyncUseCase(user.email, MatchState.DECLINED.value)
        }
    }

}
