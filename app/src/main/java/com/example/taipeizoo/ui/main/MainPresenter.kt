package com.example.taipeizoo.ui.main

import com.example.taipeizoo.model.HouseInfo
import com.example.taipeizoo.ui.base.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(
        private val view: MainContract.IMainView,
        private val repository: MainRepository
) : BasePresenter(repository), MainContract.IMainPresenter {

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
                            view.showErrorSnackBar()
                        }
                    }
                }
    }

}