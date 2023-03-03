package com.jakmall.test.app.data.remote.state

import androidx.annotation.StringRes
import com.jakmall.test.app.R
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 * Purpose to abstract the API calling state. There are 3 states: Loading, Success, Error (API Error & Throwable Error)
 */
sealed class Resource<out T> {
    object Loading:Resource<Nothing>()
    data class Success<out R>(val data:R) : Resource<R>()

    sealed class Error:Resource<Nothing>(){
        class ApiError(val status:Boolean):Error(){
            override fun errorMessage(): String {
                return if(!status) "Unexpected Error" else ""
            }

            override fun getUiMessage(): Int {
                return R.string.error_unexpected
            }

        }
        class Thrown(val exception: Throwable):Error(){
            override fun errorMessage(): String {
                return "Unexpected Error"
            }

            override fun getUiMessage(): Int {
                return when(exception){
                    is SocketTimeoutException -> R.string.error_timeout
                    is UnknownHostException, is ConnectException -> R.string.error_no_internet
                    else -> R.string.error_unexpected
                }
            }

        }
        abstract fun errorMessage():String
        @StringRes
        abstract fun getUiMessage(): Int
    }


}