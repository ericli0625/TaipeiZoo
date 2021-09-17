package com.example.taipeizoo.ui.house

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import com.example.taipeizoo.R
import com.example.taipeizoo.extension.showShimmerAnimation
import com.example.taipeizoo.extension.showTextIfNotBlank
import com.example.taipeizoo.model.House
import com.example.taipeizoo.model.Plant
import com.example.taipeizoo.ui.base.BaseFragment
import com.example.taipeizoo.util.AppBarStateChangedListener
import com.example.taipeizoo.util.Constants
import com.example.taipeizoo.util.SpaceDividerItemDecoration
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_house.*
import org.koin.android.ext.android.inject

class HouseFragment : BaseFragment<HousePresenter>(), HouseContract.IHouseView {

    override val layoutRes: Int = R.layout.fragment_house

    override val presenter by lazy { HousePresenter(this, repository) }

    private val repository: HouseRepository by inject()

    private val navController by lazy { NavHostFragment.findNavController(this) }

    private val plantAdapter by lazy { PlantAdapter(::onPlantClicked) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        presenter.viewReady(
                arguments?.getInt(Constants.HOUSE_ID) ?: 0,
                arguments?.getString(Constants.HOUSE_NAME) ?: ""
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        plantAdapter.updateData(listOf())
    }

    private fun initView() {
        toolbar.setNavigationOnClickListener { navController.popBackStack() }

        recycler_view.apply {
            adapter = plantAdapter
            addItemDecoration(SpaceDividerItemDecoration(8, dpFootSpace = 8, dpHeadSpace = 8))
        }
    }

    private fun onPlantClicked(plantName: String) {
        navController.navigate(
                R.id.action_house_to_plant,
                bundleOf(Constants.PLANT_NAME to plantName)
        )
    }

    /***** Implement Interface methods *****/

    override fun updatePlantListResult(plants: List<Plant>) {
        shimmer_view_container?.showShimmerAnimation(false)
        plantAdapter.updateData(plants)
    }

    override fun updateHouse(house: House) {
        image_banner.setImageURI(house.picUrl)

        app_bar.addOnOffsetChangedListener(object : AppBarStateChangedListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: State, offset: Int) {
                toolbar.title = if (state == State.COLLAPSED) {
                    house.name
                } else {
                    ""
                }
            }
        })

        text_detail.text = house.info
        text_memo.showTextIfNotBlank(house.memo)
        text_category.text = house.category
        text_link.text = house.url
    }

    override fun showErrorSnackBar() {
        showNetworkError()
    }
}