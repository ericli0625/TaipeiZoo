package com.example.taipeizoo.ui.plant

import com.example.taipeizoo.model.Plant
import com.example.taipeizoo.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class PlantPresenter(
        private val view: PlantContract.IPlantView
) : BasePresenter(), PlantContract.IPlantPresenter, KoinComponent {

    private val repository: IPlantRepository by inject<PlantRepository>()

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

                        }
                    }
                }
    }
}