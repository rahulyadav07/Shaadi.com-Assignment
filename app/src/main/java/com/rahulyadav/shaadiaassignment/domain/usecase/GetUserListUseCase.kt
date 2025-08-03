package com.rahulyadav.shaadiaassignment.domain.usecase

import androidx.paging.PagingData
import com.rahulyadav.shaadiaassignment.core.wrapper.Resource
import com.rahulyadav.shaadiaassignment.domain.entity.User
import com.rahulyadav.shaadiaassignment.domain.repositoryinterface.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(): Flow<Resource<PagingData<User>>> {
        return repository.getUserPaged()
    }
}