package com.rahulyadav.shaadiaassignment.di

import com.rahulyadav.shaadiaassignment.presentation.usermainscreen.UsersAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
object UserActivityProvider {

    @Provides
    fun provideUsersAdapter(): UsersAdapter {
        return UsersAdapter()
    }
}
