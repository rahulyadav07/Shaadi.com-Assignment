package com.rahulyadav.shaadiaassignment.domain.repositoryinterface

import com.rahulyadav.shaadiaassignment.core.wrapper.Resource
import com.rahulyadav.shaadiaassignment.domain.entity.User

interface UserRepository {
    suspend fun getUserListData() :Resource<List<User>>
    suspend fun userAction(id: String, action:String)
}