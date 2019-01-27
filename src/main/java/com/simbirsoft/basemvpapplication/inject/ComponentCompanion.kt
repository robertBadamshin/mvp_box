package com.simbirsoft.basemvpapplication.inject

abstract class ComponentCompanion<C : Any> {
    abstract fun createComponent(): C

    fun get(): C {
        return ComponentHolder.getOrCreate(this)
    }

    fun onFeatureFinish() {
        ComponentHolder.destroyIfNeeded(this)
    }
}
