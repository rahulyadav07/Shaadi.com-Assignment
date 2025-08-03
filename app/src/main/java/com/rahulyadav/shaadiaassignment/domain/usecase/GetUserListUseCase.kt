package com.rahulyadav.shaadiaassignment.domain.usecase

import com.rahulyadav.shaadiaassignment.core.wrapper.Resource
import com.rahulyadav.shaadiaassignment.domain.entity.User
import com.rahulyadav.shaadiaassignment.domain.repositoryinterface.UserRepository
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(): Resource<List<User>> {
        return repository.getUserListData()
    }
}