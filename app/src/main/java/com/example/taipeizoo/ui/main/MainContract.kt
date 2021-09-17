package com.example.taipeizoo.ui.main

import com.example.taipeizoo.model.House

open class MainContract {

    interface IMainPresenter {
        fun fetchHouseList()
    }

    interface IMainView {
        fun updateHouseListResult(list: List<House>)
        fun showErrorSnackBar()
    }
}