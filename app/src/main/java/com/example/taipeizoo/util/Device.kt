package com.example.taipeizoo.util

import android.content.res.Resources
import android.util.TypedValue

object Device {

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun spToPx(sp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().displayMetrics)
    }

    fun spToPx(sp: Int): Int {
        return spToPx(sp.toFloat()).toInt()
    }
}
