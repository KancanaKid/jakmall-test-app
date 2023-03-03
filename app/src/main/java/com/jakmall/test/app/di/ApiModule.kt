package com.jakmall.test.app.di

import com.jakmall.test.app.data.remote.source.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 * Purpose to provide the API class / object. It is using Dagger-Hilt library
 */

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit):Api = retrofit.create(Api::class.java)
}