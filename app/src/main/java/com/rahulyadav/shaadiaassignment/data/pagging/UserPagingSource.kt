package com.rahulyadav.shaadiaassignment.data.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rahulyadav.shaadiaassignment.core.utils.safeApiCall
import com.rahulyadav.shaadiaassignment.core.wrapper.Resource
import com.rahulyadav.shaadiaassignment.data.local.dao.UserDao
import com.rahulyadav.shaadiaassignment.data.mapper.toDomain
import com.rahulyadav.shaadiaassignment.data.mapper.toEntity
import com.rahulyadav.shaadiaassignment.data.remote.api.UserService
import com.rahulyadav.shaadiaassignment.domain.entity.User

class UserPagingSource(private val api: UserService, private val userDao: UserDao) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1) ?: state.closestPageToPosition(
                anchor
            )?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: 1

        return when (val result = safeApiCall { api.getUsers(page = page, results = params.loadSize) }) {
            is Resource.Success -> {
                val users = result.data.results.map { it.toEntity() }

                userDao.insertUsers(users)
                val domainUsers = users.map { it.toDomain() }

                LoadResult.Page(
                    data = domainUsers,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (domainUsers.isEmpty()) null else page + 1
                )
            }
            is Resource.Error -> {
                val cachedEntities = userDao.getAllUsers()
                val cachedUsers = cachedEntities.map { it.toDomain() }
                if (cachedUsers.isNotEmpty()) {
                    LoadResult.Page(
                        data = cachedUsers,
                        prevKey = null,
                        nextKey = null
                    )
                } else {
                    LoadResult.Error(result.throwable ?: Exception(result.message))
                }
            }
            Resource.Loading -> LoadResult.Invalid()
        }
    }

}
