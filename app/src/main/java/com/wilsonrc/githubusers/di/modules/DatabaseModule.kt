package com.wilsonrc.githubusers.di.modules

import androidx.room.Room
import com.wilsonrc.githubusers.base.BaseApp
import com.wilsonrc.githubusers.data.source.local.GithubUsersDatabase
import com.wilsonrc.githubusers.data.source.local.UsersDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideGithubUsersDatabase(app: BaseApp): GithubUsersDatabase = Room.databaseBuilder(app,
        GithubUsersDatabase::class.java, "github_users_db").build()

    @Provides
    @JvmStatic
    internal fun provideUsersDao(database: GithubUsersDatabase): UsersDao = database.usersDao()
}