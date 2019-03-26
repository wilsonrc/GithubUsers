package com.wilsonrc.githubusers.data.source

import com.wilsonrc.githubusers.data.models.User
import com.wilsonrc.githubusers.data.source.local.UsersLocalDataSource
import com.wilsonrc.githubusers.data.source.remote.PageLinks
import com.wilsonrc.githubusers.data.source.remote.UsersRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersRemoteDataSource: UsersRemoteDataSource,
    private val usersLocalDataSource: UsersLocalDataSource
) {

    data class OutPutPaged(var nextPage: String? = null, var users: List<User>)

    fun saveFavUser(user: User): Completable {
        return usersLocalDataSource.saveFavUser(user)
            .subscribeOn(Schedulers.io())
    }

    fun deleteFavUser(id: Int): Completable {
        return usersLocalDataSource.deleteFavUser(id)
            .subscribeOn(Schedulers.io())
    }


    fun getUsers(since: Int): Observable<OutPutPaged> {
        return usersRemoteDataSource.getUsers(since)
            .flatMap {
                val pageLinks = PageLinks(it)
                val output = OutPutPaged(pageLinks.next, it.body() ?: emptyList())
                Observable.just(output)
            }
            .subscribeOn(Schedulers.io())
    }

    fun loadMore(nextPage: String): Observable<OutPutPaged> {
        return usersRemoteDataSource.getMoreUsers(nextPage)
            .flatMap {
                val pageLinks = PageLinks(it)
                val output = OutPutPaged(pageLinks.next, it.body() ?: emptyList())
                Observable.just(output)
            }
            .subscribeOn(Schedulers.io())
    }

    fun getFavUsers(): Single<List<User>> {
        return usersLocalDataSource.getFavUsers()
            .subscribeOn(Schedulers.io())
    }
    
}