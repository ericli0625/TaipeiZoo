package com.example.taipeizoo.ui.plant

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.example.taipeizoo.R
import com.example.taipeizoo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_plant.*

class PlantFragment : BaseFragment(), PlantContract.IPlantView {

    override val layoutRes: Int = R.layout.fragment_plant

    private val presenter by lazy { PlantPresenter(this) }

    private val navController by lazy { NavHostFragment.findNavController(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        presenter.fetchPlantDetail()
    }

    private fun initView() {
        toolbar.setNavigationOnClickListener { navController.popBackStack() }
    }

    /***** Implement Interface methods *****/

    override fun updatePlantDetailResult() {

    }

}