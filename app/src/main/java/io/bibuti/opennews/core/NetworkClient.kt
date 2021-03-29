package io.bibuti.opennews.core

import io.bibuti.opennews.network.NetworkEndpoints
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * This class is to be used without dependency injections
 */
object NetworkClient {

    private const val BASE_URL = ""

    fun instance(): NetworkEndpoints {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(supportClient)
            .build()
            .create(NetworkEndpoints::class.java)
    }

    private val dataRequestInterceptor = HttpLoggingInterceptor { message ->
        Timber.d(message)
    }.setLevel(HttpLoggingInterceptor.Level.BODY)

    private val networkInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "")
            .addHeader("x-rapidapi-key", "")
            .addHeader("User-Agent", "android")
            .build()
        chain.proceed(request)
    }

    private val supportClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(networkInterceptor)
        .addInterceptor(dataRequestInterceptor)
        .connectTimeout(1, TimeUnit.MINUTES)
        .callTimeout(1, TimeUnit.MINUTES)
        .build()


}