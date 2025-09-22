package com.rahulyadav.shaadiaassignment.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.rahulyadav.shaadiaassignment.data.local.dao.UserDao
import com.rahulyadav.shaadiaassignment.data.remote.api.UserService
import com.rahulyadav.shaadiaassignment.data.remote.dto.MatchUpdateRequest
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import java.io.IOException
import java.io.*

@HiltWorker
class MatchSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val userDao: UserDao,
    private val api: UserService
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val unsyncedUsers = userDao.getUnsyncedUsers()
            if (unsyncedUsers.isNullOrEmpty()) {
                Result.success()
            }

            val matchMap = unsyncedUsers.associate {
                it.email to it.matchAction.orEmpty()
            }

            val requestBody = MatchUpdateRequest(matches = matchMap)

            val response = api.updateMatchesAction(requestBody)

            if (response.isSuccessful) {
                val updatedUsers = unsyncedUsers.map { it.copy(isSynced = true) }
                userDao.updateMatchSyncedData(updatedUsers)
                Result.success()
            } else {
                Result.retry()
            }
        } catch (e: IOException) {
            Result.retry()
        } catch (e: HttpException) {
            Result.retry()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
