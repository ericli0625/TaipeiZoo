package com.example.taipeizoo.ui.main

import com.example.taipeizoo.model.HouseInfo
import com.example.taipeizoo.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(
        private val view: MainContract.IMainView,
        private val repository: MainRepository
) : BasePresenter(repository), MainContract.IMainPresenter {

    override fun fetchHouseList() {
        repository.getAllHouseList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithAutoDispose {
                    if (it.isNotEmpty()) {
                        view.updateHouseListResult(it)
                    } else {
                        repository.fetchHouseList()
                                .subscribeWithAutoDispose { res ->
                                    when {
                                        res.isSuccess -> {
                                            val data = res.data ?: HouseInfo.defaultInstance

                                            GlobalScope.launch(Dispatchers.IO) {
                                                repository.updateHouseList(data.results)
                                            }

                                            view.updateHouseListResult(data.results)
                                        }
                                        res.isNetworkUnavailable -> {
                                            view.showErrorSnackBar()
                                        }
                                    }
                                }
                    }
                }
    }

}