package io.bibuti.opennews.core

/**
 * State Management for UI & Data.
 */
sealed class State<T> {
    class Loading<T> : State<T>()
    data class Success<T>(val data: T) : State<T>()
    data class ResponseError<T>(
        val errorCode: Int,
        val message: String,
        val errorBody: Any? = null
    ) : State<T>()

    data class ExceptionError<T>(val errorMessage: String, val throwable: Throwable? = null) :
        State<T>()
}