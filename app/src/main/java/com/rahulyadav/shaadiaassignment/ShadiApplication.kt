package com.rahulyadav.shaadiaassignment

import android.app.Application
import com.rahulyadav.shaadiaassignment.work.MatchWorkerManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ShadiApplication:Application() {

    @Inject
    lateinit var matchWorkerManager: MatchWorkerManager

    override fun onCreate() {
        super.onCreate()
        matchWorkerManager.schedulePeriodicMatchSyncWorker(this)
    }
}