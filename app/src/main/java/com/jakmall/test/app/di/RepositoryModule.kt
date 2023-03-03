package com.jakmall.test.app.di

import com.jakmall.test.app.data.remote.repositories.Repository
import com.jakmall.test.app.data.remote.repositories.RepositoryImp
import com.jakmall.test.app.data.remote.source.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.components.ViewModelComponent
import java.util.concurrent.ExecutorService
/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 * Purpose to repository object. It is using Dagger-Hilt library
 */

@Module
@InstallIn(ServiceComponent::class, ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(api: Api, executorService: ExecutorService):Repository = RepositoryImp(api, executorService)
}