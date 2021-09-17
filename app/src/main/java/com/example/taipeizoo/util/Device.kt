package com.example.taipeizoo.util

import android.content.res.Resources

object Device {

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}
