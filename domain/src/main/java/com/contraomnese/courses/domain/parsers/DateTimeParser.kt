package com.contraomnese.courses.domain.parsers

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateTimeParser {

    private const val RU_PATTERN = "d MMMM yyyy"
    private const val EN_PATTERN = "MMMM d, yyyy"

    private fun getJavaFormatter(locale: Locale): DateTimeFormatter {
        val pattern = when (locale.language) {
            "ru" -> RU_PATTERN
            else -> EN_PATTERN
        }
        return DateTimeFormatter.ofPattern(pattern, locale)
    }

    fun parseIso(input: String): String? {
        return try {
            val date = LocalDate.parse(input)
            val locale = Locale.getDefault()
            date.toJavaLocalDate().format(getJavaFormatter(locale))
        } catch (e: Exception) {
            null
        }
    }

    fun toDate(input: String): LocalDate? {
        return try {
            val locale = Locale.getDefault()
            val javaFormatter = getJavaFormatter(locale)
            val javaDate = java.time.LocalDate.parse(input, javaFormatter)
            javaDate.toKotlinLocalDate()
        } catch (e: Exception) {
            null
        }
    }
}