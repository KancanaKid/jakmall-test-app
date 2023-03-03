package com.jakmall.test.app.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import com.jakmall.test.app.R
import com.jakmall.test.app.data.model.CategoryMapResult
import com.jakmall.test.app.data.remote.state.Resource
import com.jakmall.test.app.databinding.ActivityMainBinding
import com.jakmall.test.app.ui.adapter.categories.CategoryAdapter
import com.jakmall.test.app.ui.fragment.ItemDialogBottomSheet
import com.jakmall.test.app.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.net.UnknownHostException

/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 * Purpose to act as view, showing data and UI
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CategoryAdapter.OnCategoriesListener{
    private val viewModel:AppViewModel by viewModels()
    private lateinit var viewBinding:ActivityMainBinding
    private val categoryAdapter by lazy { CategoryAdapter(this) }

    private val successSet by lazy {
        ConstraintSet().apply {
            clone(viewBinding.mainRoot)
            setVisibility(R.id.success_group, ConstraintSet.VISIBLE)
            setVisibility(R.id.loading_group, ConstraintSet.GONE)
            setVisibility(R.id.error_group, ConstraintSet.GONE)
        }
    }

    private val loadingSet by lazy {
        ConstraintSet().apply {
            clone(viewBinding.mainRoot)
            setVisibility(R.id.success_group, ConstraintSet.GONE)
            setVisibility(R.id.loading_group, ConstraintSet.VISIBLE)
            setVisibility(R.id.error_group, ConstraintSet.GONE)
        }
    }

    private val errorSet by lazy {
        ConstraintSet().apply {
            clone(viewBinding.mainRoot)
            setVisibility(R.id.success_group, ConstraintSet.GONE)
            setVisibility(R.id.loading_group, ConstraintSet.GONE)
            setVisibility(R.id.error_group, ConstraintSet.VISIBLE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.categoryRv.adapter = categoryAdapter

        setupListener()
        setupObservers()
        setupApiCall()
    }

    private fun setupListener(){
        viewBinding.refreshLayout.setOnRefreshListener {
            setupApiCall()
        }
    }

    private fun setupApiCall(){
        viewModel.getCategories()
    }

    private fun setupObservers(){
        viewModel.categoriesLiveData.observe(this){
            TransitionManager.beginDelayedTransition(viewBinding.mainRoot)
            viewBinding.refreshLayout.isRefreshing = false
            when(it){
                Resource.Loading -> {
                    loadingSet.applyTo(viewBinding.mainRoot)
                }
                is Resource.Success -> {
                    successSet.applyTo(viewBinding.mainRoot)
                    if(it.data.isNotEmpty()){
                        doPopulate(it.data)
                    }
                }
                is Resource.Error.ApiError -> {
                    errorSet.applyTo(viewBinding.mainRoot)
                    viewBinding.errorIv.apply {
                        setImageResource(R.drawable.ic_error)
                    }
                    viewBinding.errorTv.text = getString(R.string.error_api)
                }
                is Resource.Error.Thrown -> {
                    errorSet.applyTo(viewBinding.mainRoot)
                    if(it.exception is UnknownHostException){
                        viewBinding.errorIv.apply {
                            setImageResource(R.drawable.ic_no_internet)
                        }
                        viewBinding.errorTv.text = getString(R.string.error_no_internet)
                    }else{
                        viewBinding.errorIv.apply {
                            setImageResource(R.drawable.ic_error)
                        }
                        viewBinding.errorTv.text = getString(R.string.error_unexpected)
                    }

                }
            }
        }

        viewModel.resultDataMappedLiveData.observe(this){
            TransitionManager.beginDelayedTransition(viewBinding.mainRoot)
            when(it){
                is Resource.Success -> {
                    successSet.applyTo(viewBinding.mainRoot)
                    if(it.data.isNotEmpty()){
                        categoryAdapter.categoriesListMap = it.data
                    }
                }
                is Resource.Error.ApiError -> {}
                is Resource.Error.Thrown ->{}
                Resource.Loading ->{
                    loadingSet.applyTo(viewBinding.mainRoot)
                }
            }
        }
    }

    private fun doPopulate(data:List<String>){
        data.forEach { category ->
            viewModel.getJokes(category)
        }
    }

    override fun goTop(value: CategoryMapResult) {
        viewModel.goTop(value)
    }

    override fun showDialog(data: String) {
        ItemDialogBottomSheet(data).apply {
            show(supportFragmentManager,"")
        }
    }
}