package com.example.taipeizoo.ui.house

import com.example.taipeizoo.model.PlaintInfo
import com.example.taipeizoo.model.Plant
import com.example.taipeizoo.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HousePresenter(
        private val view: HouseContract.IHouseView,
        private val repository: HouseRepository
) : BasePresenter(repository), HouseContract.IHousePresenter {

    override fun viewReady(id: Int, name: String) {
        Observables.zip(repository.getHouseDetail(id), repository.getPlantList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithAutoDispose { (houseRes, plantListRes) ->

                    view.updateHouse(houseRes)

                    if (plantListRes.isNotEmpty()) {
                        view.updatePlantListResult(plantListRes)
                    } else {
                        repository.fetchPlantList(name)
                                .subscribeWithAutoDispose { res ->
                                    when {
                                        res.isSuccess -> {
                                            val data = res.data ?: PlaintInfo.defaultInstance
                                            val plants = data.results.distinctBy(Plant::nameC)

                                            GlobalScope.launch(Dispatchers.IO) {
                                                repository.updatePlantList(data.results)
                                            }

                                            view.updatePlantListResult(plants)
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