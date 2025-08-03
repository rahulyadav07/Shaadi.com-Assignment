package com.rahulyadav.shaadiaassignment.data.mapper

import com.rahulyadav.shaadiaassignment.data.local.entity.UserEntity
import com.rahulyadav.shaadiaassignment.data.remote.dto.UserDto
import com.rahulyadav.shaadiaassignment.domain.entity.User

fun UserDto.toEntity(): UserEntity {
    return UserEntity(
        email = email,
        firstName = name?.first,
        lastName = name?.last,
        imageUrl = picture?.large,
        streetName = location?.street?.name,
        streetNumber = location?.street?.number,
        city = location?.city,
        state = location?.state,
        country = location?.country,
        matchAction = "Pending",
        isSynced = false
    )
}

fun UserEntity.toDomain(): User {
    return User(
        email = email,
        name = "".plus(firstName).plus(lastName),
        imageUrl = imageUrl,
        address = streetNumber.toString().plus(streetName).plus(city).plus(state).plus(country),
        matchState = matchAction
    )
}
