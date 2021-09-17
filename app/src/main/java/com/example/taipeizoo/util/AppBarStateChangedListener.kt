package com.example.taipeizoo.util

import com.google.android.material.appbar.AppBarLayout

abstract class AppBarStateChangedListener : AppBarLayout.OnOffsetChangedListener {

    private var currentState = State.IDLE

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        when {
            verticalOffset == 0 -> {
                if (currentState != State.EXPANDED) {
                    onStateChanged(appBarLayout, State.EXPANDED, verticalOffset)
                }
                currentState = State.EXPANDED
            }
            Math.abs(verticalOffset) >= appBarLayout.totalScrollRange -> {
                if (currentState != State.COLLAPSED) {
                    onStateChanged(appBarLayout, State.COLLAPSED, verticalOffset)
                }
                currentState = State.COLLAPSED
            }
            else -> {
                onStateChanged(appBarLayout, State.IDLE, verticalOffset)
                currentState = State.IDLE
            }
        }
    }

    abstract fun onStateChanged(appBarLayout: AppBarLayout, state: State, offset: Int)

    enum class State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }
}