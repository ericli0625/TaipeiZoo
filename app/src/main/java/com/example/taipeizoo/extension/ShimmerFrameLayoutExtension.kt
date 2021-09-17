package com.example.taipeizoo.extension

import com.facebook.shimmer.ShimmerFrameLayout

fun ShimmerFrameLayout.showShimmerAnimation(isShow: Boolean) {
    if (isShow) {
        show()
        startShimmer()
    } else {
        hide()
        stopShimmer()
    }
}