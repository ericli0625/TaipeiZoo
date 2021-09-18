package com.example.taipeizoo.ui.main

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import com.example.taipeizoo.R
import com.example.taipeizoo.extension.showShimmerAnimation
import com.example.taipeizoo.model.House
import com.example.taipeizoo.ui.base.BaseFragment
import com.example.taipeizoo.util.Constants
import com.example.taipeizoo.util.SpaceDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.inject

class MainFragment : BaseFragment<MainPresenter>(), MainContract.IMainView {

    override val layoutRes: Int = R.layout.fragment_main

    override val presenter by lazy { MainPresenter(this, repository) }

    private val repository: MainRepository by inject()

    private val navController by lazy { NavHostFragment.findNavController(this) }

    private val houseAdapter by lazy { HouseAdapter(::onHouseClicked) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        presenter.fetchHouseList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        houseAdapter.updateData(listOf())
    }

    private fun initView() {
        recycler_view.apply {
            adapter = houseAdapter
            layoutAnimation = AnimationUtils.loadLayoutAnimation(
                    context,
                    R.anim.layout_animation_fall_down
            )
            addItemDecoration(SpaceDividerItemDecoration(8, dpFootSpace = 8, dpHeadSpace = 8))
        }
    }

    private fun onHouseClicked(id: Int, name: String) {
        navController.navigate(
                R.id.action_main_to_house,
                bundleOf(
                        Constants.HOUSE_ID to id,
                        Constants.HOUSE_NAME to name
                )
        )
    }

    /***** Implement Interface methods *****/

    override fun updateHouseListResult(list: List<House>) {
        shimmer_view_container?.showShimmerAnimation(false)
        houseAdapter.updateData(list)
    }

    override fun showErrorSnackBar() {
        showNetworkError()
    }
}