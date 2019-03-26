package com.wilsonrc.githubusers.di.modules

import com.wilsonrc.githubusers.ui.users.UsersActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppBindingModule {

    @ContributesAndroidInjector
    internal abstract fun provideUsersActivity(): UsersActivity

}