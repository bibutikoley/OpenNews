package io.bibuti.opennews.core

import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody

data class ErrorBodyResponse(
    @SerializedName("status")
    val status: String?, // error
    @SerializedName("code")
    val code: String?, // apiKeyInvalid
    @SerializedName("message")
    val message: String? // Your API key is invalid or incorrect. Check your key, or go to https://newsapi.org to create a free API key.
)

fun parseError(errorBody: ResponseBody?): String {
    return try {
        errorBody?.string().toString().let {
            val data =
                GsonBuilder().serializeNulls().create().fromJson(it, ErrorBodyResponse::class.java)
            "${data.code} - ${data.message}"
        }
    } catch (e: JsonParseException) {
        "Unable to parse the error."
    } catch (e: Throwable) {
        "Something went wrong with that request."
    }
}