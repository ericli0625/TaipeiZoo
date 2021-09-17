package com.example.taipeizoo.ui.house

import com.example.taipeizoo.model.PlaintInfo
import com.example.taipeizoo.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import org.koin.core.KoinComponent
import org.koin.core.inject

class HousePresenter(
        private val view: HouseContract.IHouseView
) : BasePresenter(), HouseContract.IHousePresenter, KoinComponent {

    private val repository: IHouseRepository by inject<HouseRepository>()

    override fun viewReady(id: Int, name: String) {
        Observables.zip(repository.getHouseDetail(id), repository.fetchPlantList(name))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithAutoDispose { (houseRes, plantListRes) ->
                    when {
                        plantListRes.isSuccess -> {
                            view.updateHouse(houseRes)

                            val data = plantListRes.data ?: PlaintInfo.defaultInstance
                            view.updatePlantListResult(data.results)
                        }
                        plantListRes.isNetworkUnavailable -> {

                        }
                    }
                }
    }
}