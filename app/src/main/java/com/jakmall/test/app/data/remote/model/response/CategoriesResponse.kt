package com.jakmall.test.app.data.remote.model.response

import com.google.gson.annotations.SerializedName

/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 */
data class CategoriesResponse(
    @SerializedName("error")
    val status:Boolean,
    @SerializedName("categories")
    var categories:ArrayList<String>,
    @SerializedName("categoryAliases")
    var categoryAliases:ArrayList<CategoryAliases>,
    val timeStamp:Long
)

data class CategoryAliases(
    @SerializedName("alias")
    val alias:String,
    @SerializedName("resolved")
    val resolved:String
)