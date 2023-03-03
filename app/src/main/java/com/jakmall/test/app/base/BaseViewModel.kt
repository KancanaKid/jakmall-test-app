package com.jakmall.test.app.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakmall.test.app.data.remote.state.Resource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 * Purpose to abstract data from repository, it is not using since the API response is do not have similar form
 */
abstract class BaseViewModel:ViewModel() {
    val compositeDisposable:CompositeDisposable = CompositeDisposable()

    fun <T> Single<Resource<T>>.subscribeUsing(liveData: MutableLiveData<Resource<T>>, onSuccess: ((resource: Resource.Success<T>) -> Unit)? = null): Disposable =
        this.doOnSubscribe { liveData.postValue(Resource.Loading) }
            .subscribe({
                if (it is Resource.Success) onSuccess?.invoke(it)
                liveData.postValue(it)
            }, {
                liveData.postValue(Resource.Error.Thrown(it))
            })
}