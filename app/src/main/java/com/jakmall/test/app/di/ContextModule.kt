package com.jakmall.test.app.di

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 * Purpose to provide context object. It is using Dagger-Hilt library
 */

@Module
@InstallIn(SingletonComponent::class)
object ContextModule {
    @Provides
    @Singleton
    fun provideContextResources(@ApplicationContext context: Context):Resources = context.resources
}