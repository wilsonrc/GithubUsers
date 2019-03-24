package com.wilsonrc.githubusers.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wilsonrc.githubusers.data.models.FavoriteUser

@Database(entities = [FavoriteUser::class], version = 1)
abstract class GithubUsersDatabase : RoomDatabase() {

    abstract fun usersDao(): UsersDao

}