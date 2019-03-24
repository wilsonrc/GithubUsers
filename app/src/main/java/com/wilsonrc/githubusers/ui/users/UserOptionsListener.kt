package com.wilsonrc.githubusers.ui.users

import com.wilsonrc.githubusers.data.models.User

interface UserOptionsListener {

    fun onFavoriteClicked(user: User)

}