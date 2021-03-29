package io.bibuti.opennews.core

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.*
import retrofit2.Response
import timber.log.Timber
import java.io.EOFException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException

/**
 * This is abstract wrapper for saving network responses directly to DB (with DAOs)
 */
abstract class NetworkToDBProvider<RESULT> {

    fun asFlow() = flow<State<RESULT>> {

        // Emit Loading State
        emit(State.Loading())

        // Emit Database content first
        emit(State.Success(fetchFromLocal().first()))

        // Fetch latest posts from remote
        val apiResponse = fetchFromRemote()

        // Parse body
        val responseBody = apiResponse.body()
        val errorBody = apiResponse.errorBody()

        // Check for response validation
        if (apiResponse.isSuccessful && responseBody != null) {
            // Save posts into the persistence storage
            saveRemoteData(responseBody)
        } else {
            // Something went wrong! Emit Error state.
            // Something went wrong! Emit Error state.
            when (apiResponse.code()) {
                HttpURLConnection.HTTP_UNAUTHORIZED -> emit(
                    State.ResponseError(
                        message = "Incorrect credentials",
                        errorBody = errorBody
                    )
                )
                HttpURLConnection.HTTP_BAD_GATEWAY -> emit(
                    State.ResponseError(
                        message = "Server is down",
                        errorBody = errorBody
                    )
                )
                else -> emit(
                    State.ResponseError(
                        message = parseError(errorBody), //parse your error here and show custom message
                        errorBody = errorBody
                    )
                )
            }
        }

        // Retrieve posts from persistence storage and emit
        emitAll(
            fetchFromLocal().map {
                State.Success(it)
            }
        )
    }.catch { e ->
        // Exception occurred! Emit error
        Timber.e("Error -> $e")
        when (e) {
            is IllegalStateException -> emit(
                State.ExceptionError(
                    errorMessage = "Oops! Error with data parsing",
                    throwable = e
                )
            )
            is SocketTimeoutException -> emit(
                State.ExceptionError(
                    errorMessage = "Request Timeout! try again later",
                    throwable = e
                )
            )
            is EOFException -> emit(
                State.ExceptionError(
                    errorMessage = "Response with no body",
                    throwable = e
                )
            )
            else -> emit(
                State.ExceptionError(
                    errorMessage = "Some Error Occurred! Can't get the latest data",
                    throwable = e
                )
            )
        }
        e.printStackTrace()
    }

    /**
     * Saves retrieved from remote into the persistence storage.
     */
    @WorkerThread
    protected abstract suspend fun saveRemoteData(response: RESULT)

    /**
     * Retrieves all data from persistence storage.
     */
    @MainThread
    protected abstract fun fetchFromLocal(): Flow<RESULT>

    /**
     * Fetches [Response] from the remote end point.
     */
    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<RESULT>
}
