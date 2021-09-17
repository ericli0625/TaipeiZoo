package com.example.taipeizoo.extension

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.showOrHide(isValid: Boolean?) {
    if (isValid == true) {
        show()
    } else {
        hide()
    }
}