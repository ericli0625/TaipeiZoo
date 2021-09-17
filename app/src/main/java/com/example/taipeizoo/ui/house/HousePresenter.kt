package com.example.taipeizoo.ui.house

import com.example.taipeizoo.model.PlaintInfo
import com.example.taipeizoo.model.Plant
import com.example.taipeizoo.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class HousePresenter(
        private val view: HouseContract.IHouseView
) : BasePresenter(), HouseContract.IHousePresenter, KoinComponent {

    private val repository: IHouseRepository by inject<HouseRepository>()

    override fun viewReady(id: Int, name: String) {
        repository.getHouseDetail(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithAutoDispose(view::updateHouse)

        repository.fetchPlantList(name)
                .subscribeWithAutoDispose {
                    when {
                        it.isSuccess -> {
                            val data = it.data ?: PlaintInfo.defaultInstance
                            val plantList = data.results.distinctBy(Plant::nameC)
                            view.updatePlantListResult(plantList)
                        }
                        it.isNetworkUnavailable -> {

                        }
                    }
                }
    }
}