package com.example.taipeizoo.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.toCalendar(pattern: String): Calendar =
        Calendar.getInstance().apply {
            time = this@toCalendar.toDate(pattern)
        }

fun String.toDate(pattern: String): Date =
        SimpleDateFormat(pattern, Locale.getDefault()).parse(this)