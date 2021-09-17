package com.example.taipeizoo.ui.main

import com.example.taipeizoo.model.HouseInfo
import com.example.taipeizoo.ui.base.BasePresenter
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainPresenter(
        private val view: MainContract.IMainView
) : BasePresenter(), MainContract.IMainPresenter, KoinComponent {

    private val repository: IMainRepository by inject<MainRepository>()

    override fun fetchHouseList() {

        repository.fetchHouseList()
                .subscribeWithAutoDispose {
                    when {
                        it.isSuccess -> {
                            view.updateHouseListResult(it.data ?: HouseInfo.defaultInstance)
                        }
                        it.isNetworkUnavailable -> {

                        }
                    }
                }
    }

}