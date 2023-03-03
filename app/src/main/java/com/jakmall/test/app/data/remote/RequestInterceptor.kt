package com.jakmall.test.app.data.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 * Purpose to add the interceptor as API request needed
 */
class RequestInterceptor @Inject constructor():Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original:Request = chain.request()
        val request = original.newBuilder().header("Content-Type","application/json")
            .method(original.method, original.body)
        return chain.proceed(request.build())
    }
}