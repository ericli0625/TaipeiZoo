package com.example.taipeizoo.extension

import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import com.example.taipeizoo.util.Device
import java.text.SimpleDateFormat
import java.util.*

fun String.toCalendar(pattern: String): Calendar =
        Calendar.getInstance().apply {
            time = this@toCalendar.toDate(pattern)
        }

fun String.toDate(pattern: String): Date =
        SimpleDateFormat(pattern, Locale.getDefault()).parse(this)

fun String.toPlantContentText(text: String) =
        let(::SpannableStringBuilder)
                .apply {
                    setSpan(
                            listOf(
                                    AbsoluteSizeSpan(Device.spToPx(12))
                            ),
                            text
                    )
                }