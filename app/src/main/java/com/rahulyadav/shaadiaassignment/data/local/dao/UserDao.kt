package com.rahulyadav.shaadiaassignment.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rahulyadav.shaadiaassignment.data.local.entity.UserEntity


@Dao
interface UserDao {
    @Query("SELECT * FROm users")
    suspend fun getAllUsers():List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(user:List<UserEntity>)

    @Query("UPDATE users SET matchAction = :action, isSynced = :isSync WHERE email = :email")
    suspend fun updateMatchAction(email: String, action: String, isSync: Boolean)

    @Update
    suspend fun updateMatchSyncedData(users: List<UserEntity>)


    @Query("SELECT * FROM users WHERE isSynced = 0")
    suspend fun getUnsyncedUsers(): List<UserEntity>

    @Query("UPDATE users SET isSynced = 1 WHERE email IN (:emails)")
    suspend fun markUsersAsSynced(emails: List<String>)
}