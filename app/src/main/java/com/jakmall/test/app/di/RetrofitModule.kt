package com.jakmall.test.app.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakmall.test.app.BuildConfig
import com.jakmall.test.app.data.remote.RequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 * Purpose to retrofit object. It is using Dagger-Hilt library
 */

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideBaseUrl():String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideGson():Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideRequestInterceptor():RequestInterceptor = RequestInterceptor()

    @Provides
    @Singleton
    fun provideHttpClient(requestInterceptor: RequestInterceptor):OkHttpClient =
        OkHttpClient.Builder().addInterceptor(requestInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl:String, gson: Gson,client: OkHttpClient):Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()
}