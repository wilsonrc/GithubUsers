package com.wilsonrc.githubusers.data.source.remote

import com.wilsonrc.githubusers.data.models.User
import com.wilsonrc.githubusers.data.source.UsersDataSource
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class UsersRemoteDataSource @Inject constructor(private val usersService: UsersService) : UsersDataSource {

    override fun getUsers(since: Int): Observable<User> {
        return usersService.getAllUsers(since)
    }

    override fun saveFavUser(user: User) {
        throw Exception("Save remote fav user is not allowed.")
    }

    override fun deleteFavUser(id: Int) {
        throw Exception("delete remote fav user is not allowed.")
    }

    override fun getFavUsers(): Single<List<User>> {
        throw Exception("Get fav users from remote is not allowed.")
    }

}