package com.example.taipeizoo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.taipeizoo.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<P : BasePresenter> : Fragment() {

    @get:LayoutRes
    protected abstract val layoutRes: Int
    protected abstract val presenter: P

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutRes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.registerObservables()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unRegisterObservables()
    }

    override fun onResume() {
        super.onResume()
        showNoNetworkConnectionError(presenter.isNetworkConnected)
    }

    fun showNetworkError() {
        Snackbar.make(
                requireView(),
                R.string.common_error_network_system,
                Snackbar.LENGTH_LONG
        )
                .setAction("OK") {}
                .show()
    }

    private fun showNoNetworkConnectionError(isNetworkConnected: Boolean) {
        if (isNetworkConnected) return

        Snackbar.make(
                requireView(),
                R.string.common_error_network_connection_timeout,
                Snackbar.LENGTH_LONG
        )
                .setAction("OK") {}
                .show()
    }
}