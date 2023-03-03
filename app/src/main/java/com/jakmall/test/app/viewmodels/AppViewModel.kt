package com.jakmall.test.app.viewmodels

import androidx.lifecycle.MutableLiveData
import com.jakmall.test.app.base.BaseViewModel
import com.jakmall.test.app.data.model.CategoryMapResult
import com.jakmall.test.app.data.remote.repositories.RepositoryImp
import com.jakmall.test.app.data.remote.state.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 * Purpose to provide data and or manipulation before push to UI layer
 */
@HiltViewModel
class AppViewModel @Inject constructor(private val repositoryImp: RepositoryImp):BaseViewModel(){
    val categoriesLiveData by lazy { MutableLiveData<Resource<List<String>>>() }
    var currentCategories = arrayListOf<String>()
    val resultDataMappedLiveData by lazy { MutableLiveData<Resource<ArrayList<CategoryMapResult>>>() }
    var resultDataMapped = arrayListOf<CategoryMapResult>()

    /**
     * This function getting categories string from API through repository and acted as source populating the jokes from API
     * */
    fun getCategories() = compositeDisposable.add(repositoryImp.callCategories().doOnSubscribe {
        categoriesLiveData.postValue(Resource.Loading)
    } .subscribe({
        if(it is Resource.Success){
            if(currentCategories.isNotEmpty()) currentCategories.clear()
            if(resultDataMapped.isNotEmpty()) resultDataMapped.clear()
            currentCategories.addAll(it.data.categories)
            categoriesLiveData.postValue(Resource.Success(currentCategories))
        }else if(it is Resource.Error.ApiError){
            categoriesLiveData.postValue(Resource.Error.ApiError(it.status))
        }
    },{
        categoriesLiveData.postValue(Resource.Error.Thrown(it))
    }))

    /**
     * This function calling jokes from API through repository
     * The result will be mapped to the list of data class
     * */
    fun getJokes(category:String) = compositeDisposable.add(
        repositoryImp.callJokesByCategory(category).doOnSubscribe {
            resultDataMappedLiveData.postValue(Resource.Loading)
        }.subscribe({
            if(it is Resource.Success){
                resultDataMapped.add(CategoryMapResult(category,it.data.jokes))
                resultDataMappedLiveData.postValue(Resource.Success(resultDataMapped))
            }else if(it is Resource.Error.ApiError){
                resultDataMappedLiveData.postValue(Resource.Error.ApiError(it.status))
            }
        },{
            resultDataMappedLiveData.postValue(Resource.Error.Thrown(it))
        })
    )

    /**
     * This function operate reordering category list
     * */
    fun goTop(data:CategoryMapResult){
        val copyOfData = arrayListOf<CategoryMapResult>()
        copyOfData.addAll(resultDataMapped)
        resultDataMapped.clear()
        resultDataMapped.add(0,data)

        for(i in 0 until copyOfData.size){
            if(!resultDataMapped.contains(copyOfData[i])){
                resultDataMapped.add(copyOfData[i])
            }
        }
        resultDataMappedLiveData.postValue(Resource.Success(resultDataMapped))
    }
}