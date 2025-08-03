package com.rahulyadav.shaadiaassignment.data.repository


import com.rahulyadav.shaadiaassignment.core.utils.safeApiCall
import com.rahulyadav.shaadiaassignment.core.wrapper.Resource
import com.rahulyadav.shaadiaassignment.data.local.dao.UserDao
import com.rahulyadav.shaadiaassignment.data.mapper.toDomain
import com.rahulyadav.shaadiaassignment.data.mapper.toEntity
import com.rahulyadav.shaadiaassignment.data.remote.api.UserService
import com.rahulyadav.shaadiaassignment.domain.entity.User
import com.rahulyadav.shaadiaassignment.domain.repositoryinterface.UserRepository
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(private val userService: UserService, private val userDao: UserDao) :UserRepository {
    override suspend fun getUserListData(): Resource<List<User>> {
        return when (val result = safeApiCall { userService.getUsers() }) {
            is Resource.Success -> {
                val apiUsers = result.data.results
                val entities = apiUsers.map { it.toEntity() }

                userDao.insertUsers(entities)
                Resource.Success(entities.map { it.toDomain() })
            }
            is Resource.Error -> {
                val cached = userDao.getAllUsers().map { it.toDomain() }
                if (cached.isNotEmpty()) Resource.Success(cached)
                else Resource.Error(result.message, result.throwable)
            }
            Resource.Loading -> Resource.Loading
        }
    }

    override suspend fun userAction(id: String, action: String) {
        userDao.updateMatchAction(id, action)
    }


}