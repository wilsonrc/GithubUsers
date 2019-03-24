package com.wilsonrc.githubusers.data.source.remote

import com.wilsonrc.githubusers.data.models.User
import com.wilsonrc.githubusers.data.source.UsersDataSource
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class UsersRemoteDataSource  @Inject constructor(private val usersService : UsersService) : UsersDataSource {


    override fun getUsers(since: Int): Observable<User> {
       return usersService.getAllUsers(since)
    }

    override fun getFavUsers(): Single<User> {
        throw Exception("Get fav users from remote is not allowed.")
    }

}