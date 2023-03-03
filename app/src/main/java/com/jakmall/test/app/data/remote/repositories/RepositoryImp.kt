package com.jakmall.test.app.data.remote.repositories

import com.jakmall.test.app.base.BaseRepository
import com.jakmall.test.app.data.remote.model.response.CategoriesResponse
import com.jakmall.test.app.data.remote.model.response.JokesResponse
import com.jakmall.test.app.data.remote.source.Api
import com.jakmall.test.app.data.remote.state.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleTransformer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.ExecutorService
import javax.inject.Inject

/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 * Purpose to implement to abstraction from interface. This is using the extensions function from BaseRepository as parent
 */
class RepositoryImp @Inject constructor(
    private val api:Api,
    executorService: ExecutorService,
) : Repository, BaseRepository(executorService) {
    override fun callCategories(): Single<Resource<CategoriesResponse>> {
        return api.getCategories().prepareCategoryCallRequest().addSchedulers()
    }

    override fun callJokesByCategory(category:String): Single<Resource<JokesResponse>> {
        return api.getJokesByCategory(category).prepareJokesRequest().addSchedulers()
    }
}