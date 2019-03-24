package com.wilsonrc.githubusers.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wilsonrc.githubusers.data.models.FavoriteUser
import io.reactivex.Single

@Dao
interface UsersDao {

    @Query("SELECT * FROM FavoriteUsers")
    fun getFavoriteUsers(): Single<List<FavoriteUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteUser(user: FavoriteUser)

    @Query("DELETE FROM FavoriteUsers WHERE localId = :id")
    fun deleteFavoriteUser(id: Int): Int

}