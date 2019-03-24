package com.wilsonrc.githubusers.di.component

import com.wilsonrc.githubusers.base.BaseApp
import com.wilsonrc.githubusers.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class, NetworkModule::class,
        ApiServiceModule::class,
        RepositoryModule::class,
        DataSourcesModule::class]
)
interface AppComponent : AndroidInjector<BaseApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BaseApp): Builder

        fun build(): AppComponent
    }


}