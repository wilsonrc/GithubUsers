package com.wilsonrc.githubusers.data.source

import com.wilsonrc.githubusers.data.models.User
import com.wilsonrc.githubusers.data.source.local.UsersLocalDataSource
import com.wilsonrc.githubusers.data.source.remote.UsersRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersRemoteDataSource: UsersRemoteDataSource,
    private val usersLocalDataSource: UsersLocalDataSource
) : UsersDataSource {

    override fun saveFavUser(user: User): Completable {
       return usersLocalDataSource.saveFavUser(user)
           .subscribeOn(Schedulers.io())
    }

    override fun deleteFavUser(id: Int): Completable {
       return usersLocalDataSource.deleteFavUser(id)
           .subscribeOn(Schedulers.io())
    }

    override fun getUsers(since: Int): Observable<List<User>> {
        return usersRemoteDataSource.getUsers(since)
            .subscribeOn(Schedulers.io())
    }

    override fun getFavUsers(): Single<List<User>> {
        return usersLocalDataSource.getFavUsers()
            .subscribeOn(Schedulers.io())
    }

}