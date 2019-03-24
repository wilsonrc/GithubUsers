package com.wilsonrc.githubusers.data.source.local

import com.wilsonrc.githubusers.data.models.FavoriteUser
import com.wilsonrc.githubusers.data.models.User
import com.wilsonrc.githubusers.data.source.UsersDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class UsersLocalDataSource @Inject constructor(private val usersDao: UsersDao) : UsersDataSource {

    override fun getUsers(since: Int): Observable<List<User>> {
        throw Exception("Get all users from local is not allowed.")
    }

    override fun getFavUsers(): Single<List<User>> {
        return usersDao.getFavoriteUsers()
            .flatMap { favoriteUsers ->
                Single.just(favoriteUsers.map {
                    fromFavoriteUserToUser(it)
                })
            }

    }

    override fun saveFavUser(user: User): Completable {
        return Completable.fromAction {
            val favoriteUser = fromUserToFavoriteUser(user)
            usersDao.insertFavoriteUser(favoriteUser)
        }
    }

    override fun deleteFavUser(id: Int): Completable {
        return Completable.fromAction {
            usersDao.deleteFavoriteUser(id)
        }
    }

    private fun fromFavoriteUserToUser(favoriteUser: FavoriteUser): User {
        val user = User()
        user.localId = favoriteUser.localId
        user.remoteId = favoriteUser.remoteId
        user.name = favoriteUser.name
        user.avatarUrl = favoriteUser.url
        return user
    }

    private fun fromUserToFavoriteUser(user: User): FavoriteUser {
        return FavoriteUser(remoteId = user.remoteId ?: 0, name = user.name, url = user.avatarUrl)
    }
}