package com.wilsonrc.githubusers.data.source

import com.wilsonrc.githubusers.data.models.User
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface UsersDataSource {

    fun getUsers(since: Int) : Observable<List<User>>

    fun getFavUsers() : Single<List<User>>

    fun saveFavUser(user: User): Completable

    fun deleteFavUser(id: Int): Completable

}