package com.wilsonrc.githubusers.di.modules

import com.wilsonrc.githubusers.data.source.remote.UsersRemoteDataSource
import com.wilsonrc.githubusers.data.source.remote.UsersService
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object DataSourcesModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideUsersRemoteDataSource(usersService: UsersService): UsersRemoteDataSource {
        return UsersRemoteDataSource(usersService)
    }

}