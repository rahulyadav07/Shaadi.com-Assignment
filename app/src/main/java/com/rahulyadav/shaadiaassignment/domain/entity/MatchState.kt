package com.rahulyadav.shaadiaassignment.domain.entity

enum class MatchState(val value: String) {
    ACCEPTED("Accepted"),
    DECLINED("Declined"),
    PENDING("Pending");

    override fun toString(): String = value

}
