package com.jakmall.test.app.data.remote.source

import com.jakmall.test.app.data.remote.model.response.CategoriesResponse
import com.jakmall.test.app.data.remote.model.response.JokesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 */
interface Api {
    @GET("categories")
    fun getCategories():Single<CategoriesResponse>

    @GET("joke/{category}?type=single&amount=2")
    fun getJokesByCategory(@Path("category") category:String):Single<JokesResponse>
}