package com.wilsonrc.githubusers.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteUsers")
data class FavoriteUser(
    @ColumnInfo(name = "remoteId")
    var remoteId: Int = 0,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "avatar")
    var url: String? = null
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "localId")
    var localId: Int = 0

}