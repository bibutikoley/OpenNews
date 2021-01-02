package io.bibuti.opennews.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Defining the DAOs for performing DB operations
 */
@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(news: List<SingleNewsItem>)

    @Query("select * from news_table order by newsId asc")
    fun fetchNews() : Flow<List<SingleNewsItem>>

}

/**
 * This is Database table
 */
@Entity(tableName = "news_table")
data class SingleNewsItem(
    @PrimaryKey(autoGenerate = true)
    val newsId: Long = 0L,
    val newsImageUrl: String,
    val newsTitle: String,
    val newsDescription: String,
    val newsContent: String,
    val authorName: String,
    val publishedAt: String,
)