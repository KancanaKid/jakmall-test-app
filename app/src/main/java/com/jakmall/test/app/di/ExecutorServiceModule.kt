package com.jakmall.test.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton
/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 * Purpose to executor service object. It is using Dagger-Hilt library
 */

@Module
@InstallIn(SingletonComponent::class)
object ExecutorServiceModule {

    @Provides
    @Singleton
    fun provideExecutorService():ExecutorService = Executors.newFixedThreadPool(4)
}