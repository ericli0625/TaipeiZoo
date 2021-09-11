package com.example.taipeizoo.ui.main

open class MainContract {

    interface IMainPresenter {
        fun fetchHouseList()
    }

    interface IMainView {
        fun updateHouseListResult()
    }
}