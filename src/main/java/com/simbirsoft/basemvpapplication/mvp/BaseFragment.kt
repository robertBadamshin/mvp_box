package com.simbirsoft.basemvpapplication.mvp

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatFragment
import com.simbirsoft.basemvpapplication.inject.BaseGraph
import com.simbirsoft.basemvpapplication.inject.BaseFragmentDelegate

abstract class BaseFragment<G : BaseGraph<*>> : MvpAppCompatFragment() {

    val graph by lazy { uninitializedGraph.apply { init() } }

    private val uninitializedGraph by lazy { createGraph() }
    private val featureFragmentDelegate by lazy { BaseFragmentDelegate(this) { uninitializedGraph } }

    abstract fun createGraph(): G

    override fun onStart() {
        super.onStart()
        featureFragmentDelegate.onStart()
    }

    override fun onResume() {
        super.onResume()
        featureFragmentDelegate.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        featureFragmentDelegate.onSaveInstanceState()
    }

    override fun onDestroy() {
        super.onDestroy()
        featureFragmentDelegate.onDestroy()
    }
}