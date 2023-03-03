package com.jakmall.test.app.base

import com.jakmall.test.app.data.remote.model.response.CategoriesResponse
import com.jakmall.test.app.data.remote.model.response.JokesResponse
import com.jakmall.test.app.data.remote.state.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleTransformer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.ExecutorService

/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 * Purpose to abstract API response handling code. It would love it we are have similar API response form
 */
open class BaseRepository(val executor:ExecutorService) {
    fun Single<CategoriesResponse>.prepareCategoryCallRequest():Single<Resource<CategoriesResponse>> = this.compose(
        SingleTransformer { source ->
            source.map {
                return@map if(!it.status) Resource.Success(it)
                else Resource.Error.ApiError(true)
            }.onErrorResumeNext {
                Single.just(Resource.Error.Thrown(it))
            }
        }
    )

    fun Single<JokesResponse>.prepareJokesRequest():Single<Resource<JokesResponse>> = this.compose(
        SingleTransformer { source ->
            source.map {
                return@map if(!it.status) Resource.Success(it)
                else Resource.Error.ApiError(true)
            }.onErrorResumeNext {
                Single.just(Resource.Error.Thrown(it))
            }
        }
    )

    fun <T> Single<T>.addSchedulers(): Single<T> = this.compose(addSchedulersSingle())

    private fun <T> addSchedulersSingle(): SingleTransformer<T, T> = SingleTransformer {
        return@SingleTransformer it
            .subscribeOn(Schedulers.from(executor))
            .observeOn(AndroidSchedulers.mainThread())
    }
}