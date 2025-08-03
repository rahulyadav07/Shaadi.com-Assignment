package com.rahulyadav.shaadiaassignment.domain.repositoryinterface

import androidx.paging.PagingData
import com.rahulyadav.shaadiaassignment.core.wrapper.Resource
import com.rahulyadav.shaadiaassignment.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserPaged() :Flow<Resource<PagingData<User>>>
    suspend fun userAction(id: String, action:String)
}