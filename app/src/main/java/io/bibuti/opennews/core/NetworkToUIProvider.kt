package io.bibuti.opennews.core

import androidx.annotation.MainThread
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import timber.log.Timber
import java.lang.IllegalStateException

/**
 * This is abstract wrapper for directly performing network request without saving into DB.
 */
abstract class NetworkToUIProvider<RESULT> {

    fun asFlow() = flow<State<RESULT>> {

        // Emit Loading State
        emit(State.Loading())

        // Fetch latest posts from remote
        val apiResponse = fetchFromRemote()
        val errorBody = apiResponse.errorBody()

        // Parse body
        val responseBody = apiResponse.body()

        // Check for response validation
        if (apiResponse.isSuccessful && responseBody != null) {
            // Save posts into the persistence storage
            emit(State.Success(responseBody))
        } else {
            // Something went wrong! Emit Error state.
            emit(State.ResponseError(message = apiResponse.message(), errorBody = errorBody))
        }

    }.catch { e ->
        // Exception occurred! Emit error
        Timber.e("Error -> $e")
        if (e is IllegalStateException) {
            emit(
                State.ExceptionError(
                    errorMessage = "Ohh Crap!, Error with Data Parsing",
                    throwable = e
                )
            )
        } else {
            emit(
                State.ExceptionError(
                    errorMessage = "Network error! Can't get latest data.",
                    throwable = e
                )
            )
        }
        e.printStackTrace()
    }

    /**
     * Fetches [Response] from the remote end point.
     */
    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<RESULT>
}