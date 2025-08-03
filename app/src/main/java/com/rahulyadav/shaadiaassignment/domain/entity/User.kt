package com.rahulyadav.shaadiaassignment.domain.entity

data class User(
    val email: String,
    val name: String?,
    val imageUrl: String?,
    val address:String?,
    val matchState: String?
)
