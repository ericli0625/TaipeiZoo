package com.example.taipeizoo.extension

import android.text.SpannableStringBuilder
import android.text.Spanned.SPAN_INCLUSIVE_EXCLUSIVE

fun SpannableStringBuilder.setSpan(what: Any) {
    setSpan(what, 0, length, SPAN_INCLUSIVE_EXCLUSIVE)
}

fun SpannableStringBuilder.setSpan(whats: List<Any>) {
    whats.forEach { setSpan(it, 0, length, SPAN_INCLUSIVE_EXCLUSIVE) }
}

fun SpannableStringBuilder.setSpan(whats: List<Any>, target: String) {
    val start = indexOf(target)
    val end = start + target.length
    whats.forEach { setSpan(it, start, end, SPAN_INCLUSIVE_EXCLUSIVE) }
}