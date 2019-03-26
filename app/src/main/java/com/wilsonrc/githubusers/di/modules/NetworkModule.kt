package com.wilsonrc.githubusers.di.modules

import com.wilsonrc.githubusers.BuildConfig
import com.wilsonrc.githubusers.utils.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    fun provideRetrofitInterface(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideOkHttpClient(loggingInterceptor: LoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    }

    @Provides
    @JvmStatic
    internal fun provideLoggingInterceptor(): LoggingInterceptor {
        return LoggingInterceptor()
    }

}