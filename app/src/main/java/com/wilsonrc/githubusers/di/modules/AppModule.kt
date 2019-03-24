package com.wilsonrc.githubusers.di.modules

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.wilsonrc.githubusers.base.BaseApp
import com.wilsonrc.githubusers.data.source.local.GithubUsersDatabase
import com.wilsonrc.githubusers.data.source.local.UsersDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @JvmStatic
    internal fun provideContext(app: BaseApp): Context = app

    @Provides
    @JvmStatic
    internal fun provideGithubUsersDatabase(app: BaseApp): GithubUsersDatabase {
        return Room.databaseBuilder(
            app,
            GithubUsersDatabase::class.java, "github_users_db"
        ).build()
    }

    @Provides
    @JvmStatic
    fun provideUsersDao(database: GithubUsersDatabase): UsersDao = database.UsersDao()

}

