package io.bibuti.opennews.repository

import io.bibuti.opennews.core.NetworkToUIProvider
import io.bibuti.opennews.core.State
import io.bibuti.opennews.data.apiresponses.NewsResponse
import io.bibuti.opennews.data.db.AppDB
import io.bibuti.opennews.data.db.SingleNewsItem
import io.bibuti.opennews.network.NetworkEndpoints
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * This is a repository to perform network and db operations for the given use-case
 */
class NewsRepository @Inject constructor(
    private val networkEndpoints: NetworkEndpoints,
    private val appDB: AppDB
) {

    fun getNews(): Flow<State<NewsResponse>> {
        return object : NetworkToUIProvider<NewsResponse>() {
            override suspend fun fetchFromRemote(): Response<NewsResponse> {
                val newsResponse = networkEndpoints.getNews()
                val data = newsResponse.body()?.articles?.map {
                    SingleNewsItem(
                        newsUrl = it?.url ?: "",
                        newsImageUrl = it?.urlToImage ?: "",
                        newsTitle = it?.title ?: "",
                        newsDescription = it?.description ?: "",
                        newsContent = it?.content ?: "",
                        authorName = it?.author ?: "",
                        publishedAt = it?.publishedAt ?: ""
                    )
                }
                appDB.newsDao().saveNews(data ?: listOf())
                return newsResponse
            }
        }.asFlow()
    }

    fun getNewsFromDB() = appDB.newsDao().fetchNewsDataSource()
}