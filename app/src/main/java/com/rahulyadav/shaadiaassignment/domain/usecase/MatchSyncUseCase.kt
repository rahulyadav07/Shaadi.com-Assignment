package com.rahulyadav.shaadiaassignment.domain.usecase

import com.rahulyadav.shaadiaassignment.domain.repositoryinterface.UserRepository
import javax.inject.Inject


class MatchSyncUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(email: String, action: String) {
        repository.userAction(email, action)
    }
}