package com.rahulyadav.shaadiaassignment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rahulyadav.shaadiaassignment.data.local.entity.UserEntity


@Dao
interface UserDao {
    @Query("SELECT * FROm users")
    suspend fun getAllUsers():List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(user:List<UserEntity>)

    @Query("UPDATE users SET matchAction = :action WHERE email = :email")
    suspend fun updateMatchAction(email: String, action: String)


    @Query("DELETE FROM users")
    suspend fun clearAll()
}