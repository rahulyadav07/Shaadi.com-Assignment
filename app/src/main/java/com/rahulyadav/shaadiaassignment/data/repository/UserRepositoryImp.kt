package com.rahulyadav.shaadiaassignment.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rahulyadav.shaadiaassignment.core.utils.safeApiCall
import com.rahulyadav.shaadiaassignment.core.wrapper.Resource
import com.rahulyadav.shaadiaassignment.data.local.dao.UserDao
import com.rahulyadav.shaadiaassignment.data.pagging.UserPagingSource
import com.rahulyadav.shaadiaassignment.data.remote.api.UserService
import com.rahulyadav.shaadiaassignment.data.remote.dto.MatchUpdateRequest
import com.rahulyadav.shaadiaassignment.domain.entity.User
import com.rahulyadav.shaadiaassignment.domain.repositoryinterface.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val api: UserService,
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUserPaged(): Flow<Resource<PagingData<User>>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { UserPagingSource(api, userDao) }
        ).flow
            .map<PagingData<User>, Resource<PagingData<User>>> { pagingData ->
                Resource.Success(pagingData)
            }
            .catch { e ->
                emit(Resource.Error(e.message ?: "Unknown error", e))
            }
    }

    override suspend fun userAction(email: String, action: String) {

        val response = safeApiCall {
            val matchMap = mapOf(email to action)
            api.updateMatchesAction(MatchUpdateRequest(matches = matchMap))
        }


        if (response is Resource.Success && response.data.isSuccessful) {
            userDao.updateMatchAction(email, action, true)
        }
        else {

            userDao.updateMatchAction(email, action, true) //  it will false i am just adding to simulate the behaviour
        }
    }
}