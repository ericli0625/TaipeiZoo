package com.example.taipeizoo.ui.plant

import com.example.taipeizoo.model.Plant
import com.example.taipeizoo.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers

class PlantPresenter(
        private val view: PlantContract.IPlantView,
        private val repository: PlantRepository
) : BasePresenter(repository), PlantContract.IPlantPresenter {

    override fun fetchPlantDetail(query: String) {
        repository.fetchPlantDetail(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWithAutoDispose {
                    when {
                        it.isSuccess -> {
                            val data = it.data?.results?.firstOrNull() ?: Plant.defaultInstance
                            view.updatePlantDetailResult(data)
                        }
                        it.isNetworkUnavailable -> {
                            view.showErrorSnackBar()
                        }
                    }
                }
    }
}