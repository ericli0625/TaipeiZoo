package com.example.taipeizoo.ui.plant

class PlantContract {

    interface IPlantPresenter {
        fun fetchPlantDetail()
    }

    interface IPlantView {
        fun updatePlantDetailResult()
    }
}
