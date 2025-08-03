package com.rahulyadav.shaadiaassignment.core.utils

import com.rahulyadav.shaadiaassignment.core.wrapper.Resource
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): Resource<T> {
    return try {
        val result = apiCall()
        Resource.Success(result)
    } catch (e: IOException) {
        Resource.Error("Network error. Please check your internet connection.", e)
    } catch (e: HttpException) {
        val errorMsg = "HTTP ${e.code()} - ${e.message()}"
        Resource.Error(errorMsg, e)
    } catch (e: Exception) {
        Resource.Error("Unexpected error: ${e.localizedMessage ?: "Unknown"}", e)
    }
}
