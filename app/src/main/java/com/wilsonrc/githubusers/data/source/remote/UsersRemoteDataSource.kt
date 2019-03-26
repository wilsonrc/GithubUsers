package com.wilsonrc.githubusers.data.source.remote

import com.wilsonrc.githubusers.data.models.User
import com.wilsonrc.githubusers.data.source.UsersDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class UsersRemoteDataSource @Inject constructor(private val usersService: UsersService) :
    UsersDataSource {

    override fun getUsers(since: Int): Observable<Response<List<User>>> {
        return usersService.getAllUsers(since)
    }

    override fun getMoreUsers(url: String): Observable<Response<List<User>>> {
        return usersService.getMoreUsers(url)
    }


    override fun saveFavUser(user: User): Completable {
        throw Exception("Save remote fav user is not allowed.")
    }

    override fun deleteFavUser(id: Int): Completable {
        throw Exception("delete remote fav user is not allowed.")
    }

    override fun getFavUsers(): Single<List<User>> {
        throw Exception("Get fav users from remote is not allowed.")
    }

}