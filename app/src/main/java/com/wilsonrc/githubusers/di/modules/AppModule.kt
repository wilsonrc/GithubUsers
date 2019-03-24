package com.wilsonrc.githubusers.di.modules

import android.content.Context
import com.wilsonrc.githubusers.base.BaseApp
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract internal fun provideContext(app: BaseApp) : Context


}