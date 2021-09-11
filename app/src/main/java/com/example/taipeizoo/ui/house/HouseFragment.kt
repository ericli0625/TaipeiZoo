package com.example.taipeizoo.ui.house

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import com.example.taipeizoo.R
import com.example.taipeizoo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_house.*

class HouseFragment : BaseFragment(), HouseContract.IHouseView {

    override val layoutRes: Int = R.layout.fragment_house

    private val presenter by lazy { HousePresenter(this) }

    private val navController by lazy { NavHostFragment.findNavController(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        presenter.fetchPlantList()
    }

    private fun initView() {
        toolbar.setNavigationOnClickListener { navController.popBackStack() }

        button.setOnClickListener {
            navController.navigate(R.id.action_house_to_plant, bundleOf())
        }
    }

    /***** Implement Interface methods *****/

    override fun updatePlantListResult() {

    }

}