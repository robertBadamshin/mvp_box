package com.simbirsoft.basemvpapplication.inject

abstract class BaseGraph<C : Any>(
    private val companion: ComponentCompanion<C>
) {

    fun init() {
        inject(companion.get())
    }

    abstract fun inject(component: C)

    fun onFeatureFinish() {
        companion.onFeatureFinish()
    }
}