package com.wilsonrc.githubusers.data.source.remote

import com.wilsonrc.githubusers.data.models.User
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface UsersService {

    @GET("/users")
    fun getAllUsers(@Query("since") since : Int) :  Observable<Response<List<User>>>

    @GET
    fun getMoreUsers(@Url url : String) :  Observable<Response<List<User>>>

}