package com.example.taipeizoo.ui.house

import com.example.taipeizoo.extension.toCalendar
import com.example.taipeizoo.model.PlaintInfo
import com.example.taipeizoo.model.Plant
import com.example.taipeizoo.ui.base.BasePresenter
import com.example.taipeizoo.util.Constants.DATE_PATTERN_SLASH_SEPARATE
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
        Observables.zip(repository.getHouseDetail(id), repository.getTargetHousePlantList(name))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithAutoDispose { (houseRes, plantListRes) ->
                    view.updateHouse(houseRes)
                    view.updatePlantListResult(plantListRes, true)
                }

        Observables.zip(repository.fetchPlantList(name), repository.getPlantList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithAutoDispose { (res, plantList) ->
                    when {
                        res.isSuccess -> {
                            val data = res.data ?: PlaintInfo.defaultInstance
                            val plantsRes = data.results.distinctBy(Plant::nameC)
                            updateLatestPlantData(plantsRes, plantList)
                        }
                        res.isNetworkUnavailable -> {
                            view.showErrorSnackBar()
                        }
                    }
                }
    }

    private fun updateLatestPlantData(plantsRes: List<Plant>, plantList: List<Plant>) {
        plantsRes.forEach { plant ->
            val targetPlant = plantList
                    .firstOrNull { it.id == plant.id }
                    ?: Plant.defaultInstance

            if (targetPlant == Plant.defaultInstance) {
                GlobalScope.launch(Dispatchers.IO) {
                    repository.updatePlant(plant)
                }
            } else {
                val plantsResTime = plant.updateDate.toCalendar(DATE_PATTERN_SLASH_SEPARATE)
                val targetPlantTime = targetPlant.updateDate.toCalendar(DATE_PATTERN_SLASH_SEPARATE)

                if (plantsResTime.timeInMillis > targetPlantTime.timeInMillis) {
                    GlobalScope.launch(Dispatchers.IO) {
                        repository.updatePlant(plant)
                    }
                }
            }
        }
        view.updatePlantListResult(plantsRes, true)
    }
}