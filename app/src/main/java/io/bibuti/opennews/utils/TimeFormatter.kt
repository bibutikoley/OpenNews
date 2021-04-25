package io.bibuti.opennews.utils

import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object TimeFormatter {

    private const val defaultFormat = "dd MMM, yyyy ● hh:mm a"
    fun formatZonedTime(utc: String?, desiredFormat: String?): String {
        return try {
            if (utc.isNullOrEmpty()) {
                return ""
            }
            val format = DateTimeFormatter.ofPattern(desiredFormat ?: defaultFormat)
            val zonedInstant = ZonedDateTime.parse(utc)
            val localUtcTime = LocalDateTime.ofInstant(zonedInstant.toInstant(), zonedInstant.zone)
            localUtcTime.format(format).trim()
        } catch (e: DateTimeParseException) {
            ""
        } catch (e: Exception) {
            ""
        }
    }

}