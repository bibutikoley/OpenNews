package io.bibuti.opennews.core

import androidx.annotation.MainThread
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import timber.log.Timber
import java.io.EOFException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException

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
            when (apiResponse.code()) {
                HttpURLConnection.HTTP_UNAUTHORIZED -> emit(
                    State.ResponseError(
                        errorCode = apiResponse.code(),
                        message = "Incorrect credentials",
                        errorBody = errorBody
                    )
                )
                HttpURLConnection.HTTP_BAD_GATEWAY -> emit(
                    State.ResponseError(
                        errorCode = apiResponse.code(),
                        message = "Server is down",
                        errorBody = errorBody
                    )
                )
                else -> emit(
                    State.ResponseError(
                        errorCode = apiResponse.code(),
                        message = parseError(errorBody), //parse your error here and show custom message
                        errorBody = errorBody
                    )
                )
            }
        }

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
     * Fetches [Response] from the remote end point.
     */
    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<RESULT>
}