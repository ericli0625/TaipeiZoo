package com.example.taipeizoo.ui.main

import com.example.taipeizoo.model.HouseInfo
import com.example.taipeizoo.ui.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
                            val data = it.data ?: HouseInfo.defaultInstance

                            GlobalScope.launch(Dispatchers.IO) {
                                repository.updateHouseList(data.results)
                            }

                            view.updateHouseListResult(data)
                        }
                        it.isNetworkUnavailable -> {

                        }
                    }
                }
    }

}