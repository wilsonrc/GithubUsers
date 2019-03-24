package com.wilsonrc.githubusers.di.modules

import com.wilsonrc.githubusers.data.source.UsersRepository
import com.wilsonrc.githubusers.data.source.remote.UsersRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object RepositoryModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideUsersRepository(usersRemoteDataSource: UsersRemoteDataSource) : UsersRepository{
        return UsersRepository(usersRemoteDataSource)
    }

}