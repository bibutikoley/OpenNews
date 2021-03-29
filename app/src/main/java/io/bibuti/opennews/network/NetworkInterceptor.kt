package io.bibuti.opennews.network

import io.bibuti.opennews.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

/**
 * This is a network interceptor
 * This is used for adding additional params to all the request made via app.
 */
class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val addQueryParameter = originalRequest.url.newBuilder()
            .addQueryParameter("country", Locale.getDefault().country.toString())
            .addQueryParameter("apiKey", BuildConfig.NEWS_API_KEY)
            .build()
        val modifiedRequest = originalRequest.newBuilder()
            .addHeader("User-Agent", "android")
            .url(addQueryParameter)
            .build()
        return chain.proceed(modifiedRequest)
    }
}