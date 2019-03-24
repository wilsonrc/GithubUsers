package com.wilsonrc.githubusers.data.source

import com.wilsonrc.githubusers.data.models.User
import com.wilsonrc.githubusers.data.source.local.UsersLocalDataSource
import com.wilsonrc.githubusers.data.source.remote.UsersRemoteDataSource
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersRemoteDataSource: UsersRemoteDataSource,
    private val usersLocalDataSource: UsersLocalDataSource
) : UsersDataSource {

    override fun saveFavUser(user: User) {
        usersLocalDataSource.saveFavUser(user)
    }

    override fun deleteFavUser(id: Int) {
        usersLocalDataSource.deleteFavUser(id)
    }

    override fun getUsers(since: Int): Observable<User> {
        return usersRemoteDataSource.getUsers(since)
    }

    override fun getFavUsers(): Single<List<User>> {
        return usersLocalDataSource.getFavUsers()
    }

}