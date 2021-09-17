package com.example.taipeizoo.ui.plant

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.example.taipeizoo.R
import com.example.taipeizoo.extension.showShimmerAnimation
import com.example.taipeizoo.extension.showTextIfNotBlank
import com.example.taipeizoo.model.Plant
import com.example.taipeizoo.ui.base.BaseFragment
import com.example.taipeizoo.util.Constants
import kotlinx.android.synthetic.main.fragment_plant.*
import org.koin.android.ext.android.inject

class PlantFragment : BaseFragment<PlantPresenter>(), PlantContract.IPlantView {

    override val layoutRes: Int = R.layout.fragment_plant

    override val presenter by lazy { PlantPresenter(this, repository) }

    private val repository: PlantRepository by inject()

    private val navController by lazy { NavHostFragment.findNavController(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        presenter.fetchPlantDetail(
                arguments?.getString(Constants.PLANT_NAME) ?: ""
        )
    }

    private fun initView() {
        toolbar.setNavigationOnClickListener { navController.popBackStack() }
    }

    /***** Implement Interface methods *****/

    override fun updatePlantDetailResult(plant: Plant) {
        shimmer_view_container?.showShimmerAnimation(false)

        image_banner.setImageURI(plant.pic01Url)
        text_title_c.text = plant.nameC
        text_title_e.text = plant.nameE

        text_desc.showTextIfNotBlank(requireContext().getString(R.string.plant_alias, plant.alsoKnown))
        text_brief.showTextIfNotBlank(requireContext().getString(R.string.plant_brief, plant.brief))
        text_feature.showTextIfNotBlank(requireContext().getString(R.string.plant_feature, plant.feature))
        text_usage.showTextIfNotBlank(requireContext().getString(R.string.plant_usage, plant.usage))
    }

    override fun showErrorSnackBar() {
        showNetworkError()
    }
}