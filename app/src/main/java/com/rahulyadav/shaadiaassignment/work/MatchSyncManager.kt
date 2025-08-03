package com.rahulyadav.shaadiaassignment.work



import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.rahulyadav.shaadiaassignment.core.utils.MATCH_SYNC_WORKER
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MatchWorkerManager @Inject constructor(
    private val workerFactory: HiltWorkerFactory
) {

    fun schedulePeriodicMatchSyncWorker(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val periodicRequest = PeriodicWorkRequestBuilder<MatchSyncWorker>(
            6, TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            MATCH_SYNC_WORKER,
            ExistingPeriodicWorkPolicy.KEEP,
            periodicRequest
        )
    }

}
