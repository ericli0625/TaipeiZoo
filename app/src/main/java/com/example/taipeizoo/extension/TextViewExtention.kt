package com.example.taipeizoo.extension

import android.view.View
import android.widget.TextView

fun TextView.showTextIfNotBlank(text: CharSequence?) {
    if (text.isNullOrBlank()) {
        visibility = View.GONE
    } else {
        visibility = View.VISIBLE
        this.text = text
    }
}