package com.example.taipeizoo.ui.main

import com.example.taipeizoo.ui.base.BasePresenter

class MainPresenter(
        private val view: MainContract.IMainView,
) : BasePresenter(), MainContract.IMainPresenter {

    override fun fetchHouseList() {
        view.updateHouseListResult()
    }

}