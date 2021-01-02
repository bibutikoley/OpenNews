package io.bibuti.opennews.network

import io.bibuti.opennews.data.apiresponses.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Define all the network endpoints in this file
 */
interface NetworkEndpoints {

    @GET("top-headlines")
    suspend fun getNews(): Response<NewsResponse>

}