package com.example.taipeizoo.ui.plant

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.example.taipeizoo.R
import com.example.taipeizoo.model.Plant
import com.example.taipeizoo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_plant.*

class PlantFragment : BaseFragment<PlantPresenter>(), PlantContract.IPlantView {

    override val layoutRes: Int = R.layout.fragment_plant

    override val presenter by lazy { PlantPresenter(this) }

    private val navController by lazy { NavHostFragment.findNavController(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        presenter.fetchPlantDetail("九芎")
    }

    private fun initView() {
        toolbar.setNavigationOnClickListener { navController.popBackStack() }
    }

    /***** Implement Interface methods *****/

    override fun updatePlantDetailResult(plant: Plant) {
        Log.e("eric", " PlantFragment, updatePlantDetailResult = $plant")
    }

}