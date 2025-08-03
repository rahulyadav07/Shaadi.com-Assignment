package com.rahulyadav.shaadiaassignment.data.remote.api

import retrofit2.http.GET

interface UserService {
    @GET("/api/?results=10")
    suspend fun getUsers(): UserApiResponseDto
}
