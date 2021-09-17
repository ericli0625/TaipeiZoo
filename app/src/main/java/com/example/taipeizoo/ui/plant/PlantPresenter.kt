package com.example.taipeizoo.ui.plant

import com.example.taipeizoo.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers

class PlantPresenter(
        private val view: PlantContract.IPlantView,
        private val repository: PlantRepository
) : BasePresenter(repository), PlantContract.IPlantPresenter {

    override fun fetchPlantDetail(query: String) {
        repository.getPlantDetail(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithAutoDispose(view::updatePlantDetailResult)
    }
}