package com.wilsonrc.githubusers.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteUsers")
data class FavoriteUser(
    @ColumnInfo(name = "remoteId")
    val remoteId: Int = 0,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "avatar")
    val url: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "localId")
    val localId: Int = 0
}