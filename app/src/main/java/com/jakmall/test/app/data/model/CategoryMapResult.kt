package com.jakmall.test.app.data.model

import com.jakmall.test.app.data.remote.model.response.Joke

/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 * Purpose to handle the data manipulation on view-model class as per presentation requirement
 */
data class CategoryMapResult(
    val category:String,
    val jokes:ArrayList<Joke>
)