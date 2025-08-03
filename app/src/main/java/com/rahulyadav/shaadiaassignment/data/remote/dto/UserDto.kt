package com.rahulyadav.shaadiaassignment.data.remote.dto

import com.google.gson.annotations.SerializedName


data class UserDto(
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: NameDto?,
    @SerializedName("picture") val picture: PictureDto?,
    @SerializedName("location") val location: LocationDto?,

) {
    data class NameDto(
        @SerializedName("first") val first: String?,
        @SerializedName("last") val last: String?
    )
    data class PictureDto(
        @SerializedName("large") val large: String?,
        @SerializedName("medium") val medium: String?,
        @SerializedName("thumbnail") val thumbnail: String?
    )
    data class LocationDto(
        @SerializedName("street") val street: StreetDto?,
        @SerializedName("city") val city: String?,
        @SerializedName("state") val state: String?,
        @SerializedName("country") val country: String?
    ) {
        data class StreetDto(
            @SerializedName("name") val name: String?,
            @SerializedName("number") val number: Int?
        )
    }
}
