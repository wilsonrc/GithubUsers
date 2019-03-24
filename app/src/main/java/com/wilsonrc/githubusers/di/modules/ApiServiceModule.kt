package com.wilsonrc.githubusers.di.modules

import com.wilsonrc.githubusers.data.source.remote.UsersService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module
object ApiServiceModule {

    @Provides
    @Reusable
    @JvmStatic
    fun provideUsersService(retrofit: Retrofit): UsersService {
        return retrofit.create(UsersService::class.java)
    }

}