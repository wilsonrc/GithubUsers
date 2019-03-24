package com.wilsonrc.githubusers.data.source.remote

import com.wilsonrc.githubusers.data.models.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface UsersService {

    @GET("/users")
    fun getAllUsers(@Query("since") since : Int) : Observable<User>

}