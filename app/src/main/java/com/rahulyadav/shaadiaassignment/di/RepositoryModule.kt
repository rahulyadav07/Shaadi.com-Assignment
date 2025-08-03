package com.rahulyadav.shaadiaassignment.di

import com.rahulyadav.shaadiaassignment.data.repository.UserRepositoryImp
import com.rahulyadav.shaadiaassignment.domain.repositoryinterface.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepositoryImp: UserRepositoryImp):UserRepository
}