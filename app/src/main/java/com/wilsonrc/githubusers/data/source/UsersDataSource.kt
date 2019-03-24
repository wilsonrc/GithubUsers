package com.wilsonrc.githubusers.data.source

import com.wilsonrc.githubusers.data.models.User
import io.reactivex.Observable
import io.reactivex.Single

interface UsersDataSource {

    fun getUsers(since: Int) : Observable<User>

    fun getFavUsers() : Single<User>

}