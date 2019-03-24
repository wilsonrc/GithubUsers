package com.wilsonrc.githubusers.data.source

import com.wilsonrc.githubusers.data.models.User
import com.wilsonrc.githubusers.data.source.remote.UsersRemoteDataSource
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class UsersRepository @Inject constructor(private val usersRemoteDataSource : UsersRemoteDataSource) : UsersDataSource {

    override fun getUsers(since: Int): Observable<User> {
        return usersRemoteDataSource.getUsers(since)
    }

    override fun getFavUsers(): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}