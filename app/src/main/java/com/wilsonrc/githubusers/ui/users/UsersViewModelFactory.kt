package com.wilsonrc.githubusers.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class UsersViewModelFactory @Inject constructor(private val usersViewModel: UsersViewModel) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return usersViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}