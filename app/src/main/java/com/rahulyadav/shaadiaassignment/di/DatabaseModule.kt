package com.rahulyadav.shaadiaassignment.di

import android.content.Context
import androidx.room.Room
import com.rahulyadav.shaadiaassignment.core.utils.USER_DATABASE
import com.rahulyadav.shaadiaassignment.data.local.dao.UserDao
import com.rahulyadav.shaadiaassignment.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context):AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, USER_DATABASE).build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase):UserDao {
        return database.usersDao()
    }
}