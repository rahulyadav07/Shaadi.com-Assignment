package com.rahulyadav.shaadiaassignment.data.remote.api

import com.rahulyadav.shaadiaassignment.data.remote.dto.MatchUpdateRequest
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @GET("/api/")
    suspend fun getUsers(@Query("page") page: Int, @Query("results") results: Int ): UserApiResponseDto


    @POST("apiEndPoint")
    suspend fun updateMatchesAction(
        @Body request: MatchUpdateRequest
    ): Response
}
