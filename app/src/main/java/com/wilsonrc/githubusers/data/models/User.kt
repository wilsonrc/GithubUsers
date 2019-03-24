package com.wilsonrc.githubusers.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("login")
    @Expose
    var name: String? = null

    @SerializedName("id")
    @Expose
    var remoteId: Int? = null

    var localId: Int? = null

    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null
}