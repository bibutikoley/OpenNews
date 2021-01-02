package io.bibuti.opennews.di

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.bibuti.opennews.data.db.AppDB
import io.bibuti.opennews.network.NetworkEndpoints
import io.bibuti.opennews.network.NetworkInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * This is dagger-hilt module to perform the dependency injections
 */
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    //Network..
    @Singleton
    @Provides
    fun provideBaseUrl() = "https://newsapi.org/v2/"

    @Singleton
    @Provides
    fun provideNetworkInterceptor() = NetworkInterceptor()

    @Singleton
    @Provides
    fun provideDataRequestInterceptor() = HttpLoggingInterceptor { message ->
        Timber.d(message)
    }.apply { level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun provideSupportClient(
        networkInterceptor: NetworkInterceptor,
        dataRequestInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient()
        .newBuilder()
        .addInterceptor(networkInterceptor)
        .addInterceptor(dataRequestInterceptor)
        .connectTimeout(1, TimeUnit.MINUTES)
        .callTimeout(1, TimeUnit.MINUTES)
        .build()

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideGsonFactory(gson: Gson?): GsonConverterFactory = GsonConverterFactory.create(gson ?: Gson())

    @Singleton
    @Provides
    fun provideNetworkBuilder(
        baseUrl: String,
        supportClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(gsonConverterFactory)
        .client(supportClient)
        .build()

    @Singleton
    @Provides
    fun provideNetworkInstance(networkBuilder: Retrofit): NetworkEndpoints = networkBuilder
        .create(NetworkEndpoints::class.java)


    //Local db..
    @Singleton
    @Provides
    fun provideDatabase(application: Application) = AppDB.getDatabase(application)

}