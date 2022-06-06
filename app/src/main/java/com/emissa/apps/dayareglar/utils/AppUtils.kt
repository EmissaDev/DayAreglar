package com.emissa.apps.dayareglar.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


const val FULL_DATE_PATTERN = "EEE MMM dd HH:mm:ss ZZZZZ yyyy"
val DATE_FORMAT = SimpleDateFormat(FULL_DATE_PATTERN, Locale.ENGLISH)


fun getDate(eventDate: String) : String {
    var time = ""
    DATE_FORMAT.isLenient = true

    try {
        val difference = (System.currentTimeMillis() - DATE_FORMAT.parse(eventDate)!!.time) / 1000
        val then = Calendar.getInstance()
        then.time = DATE_FORMAT.parse(eventDate) as Date
        val date = then.time

        time = when {
            difference < (60 * 60 * 24) -> {
                val dateFormat = SimpleDateFormat("HH:mm a")
                dateFormat.format(date)
            }
            else -> {
                val dateFormat = SimpleDateFormat("MMM d, yyyy")
                dateFormat.format(date)
            }
        }
    } catch (ex: ParseException) {
        ex.printStackTrace()
    }

    return time
}

fun getFullTimeStamp(rawDate: String) : String {
    var time = ""
    DATE_FORMAT.isLenient = true
    try {
        val then = Calendar.getInstance()
        then.time = DATE_FORMAT.parse(rawDate) as Date
        val date = then.time
        val dateFormat = SimpleDateFormat("EEE, d MMM yyyy")

        time = dateFormat.format(date)
    } catch (ex: ParseException) {
        ex.printStackTrace()
    }

    return time
}
