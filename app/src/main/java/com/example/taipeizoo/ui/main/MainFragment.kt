package com.example.taipeizoo.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import com.example.taipeizoo.R
import com.example.taipeizoo.model.HouseInfo
import com.example.taipeizoo.ui.base.BaseFragment
import com.example.taipeizoo.util.Constants
import com.example.taipeizoo.util.SpaceDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment<MainPresenter>(), MainContract.IMainView {

    override val layoutRes: Int = R.layout.fragment_main

    override val presenter by lazy { MainPresenter(this) }

    private val navController by lazy { NavHostFragment.findNavController(this) }

    private val houseAdapter by lazy { HouseAdapter(::onHouseClicked) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        presenter.fetchHouseList()
    }

    private fun initView() {
        recycler_view.apply {
            adapter = houseAdapter
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

    override fun updateHouseListResult(data: HouseInfo) {
        houseAdapter.updateData(data)
    }
}