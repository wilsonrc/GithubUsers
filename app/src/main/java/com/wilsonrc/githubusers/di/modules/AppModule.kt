package com.wilsonrc.githubusers.di.modules

import android.content.Context
import com.wilsonrc.githubusers.base.BaseApp
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @Provides
    @JvmStatic
    internal fun provideContext(app: BaseApp): Context = app

}

