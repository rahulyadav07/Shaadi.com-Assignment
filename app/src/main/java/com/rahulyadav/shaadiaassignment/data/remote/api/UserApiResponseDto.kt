package com.rahulyadav.shaadiaassignment.data.remote.api

import com.google.gson.annotations.SerializedName
import com.rahulyadav.shaadiaassignment.data.remote.dto.UserDto

data class UserApiResponseDto(
    @SerializedName("results") val results: List<UserDto>
)