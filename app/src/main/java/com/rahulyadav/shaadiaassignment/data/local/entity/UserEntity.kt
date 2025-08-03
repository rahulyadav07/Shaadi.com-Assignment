package com.rahulyadav.shaadiaassignment.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val email: String,
    val firstName: String?,
    val lastName: String?,
    val imageUrl: String?,
    val streetName: String?,
    val streetNumber: Int?,
    val city: String?,
    val state: String?,
    val country: String?,
    val matchAction:String?,
    val isSynced :Boolean = true
)