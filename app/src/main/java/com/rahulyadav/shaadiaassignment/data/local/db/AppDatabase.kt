package com.rahulyadav.shaadiaassignment.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rahulyadav.shaadiaassignment.data.local.dao.UserDao
import com.rahulyadav.shaadiaassignment.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    abstract fun usersDao():UserDao
}