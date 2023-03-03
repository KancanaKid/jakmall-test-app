package com.jakmall.test.app.data.remote.repositories

import com.jakmall.test.app.data.remote.model.response.CategoriesResponse
import com.jakmall.test.app.data.remote.model.response.JokesResponse
import com.jakmall.test.app.data.remote.state.Resource
import io.reactivex.rxjava3.core.Single

/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 * Purpose to abstract the requirements
 */
interface Repository {
    fun callCategories():Single<Resource<CategoriesResponse>>
    fun callJokesByCategory(category:String):Single<Resource<JokesResponse>>
}