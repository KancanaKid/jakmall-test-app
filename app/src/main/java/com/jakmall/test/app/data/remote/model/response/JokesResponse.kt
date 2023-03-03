package com.jakmall.test.app.data.remote.model.response

import com.google.gson.annotations.SerializedName

/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 */
data class JokesResponse (
    @SerializedName("error")
    val status:Boolean,
    @SerializedName("amount")
    val amount:Int,
    @SerializedName("jokes")
    val jokes:ArrayList<Joke>
    )

data class Joke(
    @SerializedName("category")
    val category:String,
    @SerializedName("type")
    val type:String,
    @SerializedName("joke")
    val joke:String,
    @SerializedName("flags")
    val flag: Flag,
    @SerializedName("id")
    val id:Int,
    @SerializedName("safe")
    val safe:Boolean,
    @SerializedName("lang")
    val lang:String
)

data class Flag(
    @SerializedName("nsfw")
    val nsfw:Boolean,
    @SerializedName("religious")
    val religious:Boolean,
    @SerializedName("political")
    val political:Boolean,
    @SerializedName("racist")
    val racist:Boolean,
    @SerializedName("sexist")
    val sexist:Boolean,
    @SerializedName("explicit")
    val explicit:Boolean
)