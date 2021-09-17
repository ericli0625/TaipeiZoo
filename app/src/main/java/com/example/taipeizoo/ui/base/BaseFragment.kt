package com.example.taipeizoo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment<P : BasePresenter> : Fragment() {

    @get:LayoutRes
    protected abstract val layoutRes: Int
    protected abstract val presenter: P

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutRes, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.registerObservables()
    }

    override fun onStop() {
        super.onStop()
        presenter.unRegisterObservables()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

}