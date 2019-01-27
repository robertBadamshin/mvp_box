package com.simbirsoft.basemvpapplication.inject

import android.support.v4.app.Fragment

class BaseFragmentDelegate(
    private val fragment: Fragment,
    private val graphProvider: () -> BaseGraph<*>
) {

    private var isSaved: Boolean = false

    fun onStart() {
        isSaved = false
    }

    fun onResume() {
        isSaved = false
    }

    fun onSaveInstanceState() {
        isSaved = true
    }

    fun onDestroy() {

        // We leave the screen and respectively all fragments will be destroyed
        if (fragment.requireActivity().isFinishing) {
            graphProvider.invoke().onFeatureFinish()
            return
        }

        // When we rotate device isRemoving() return true for fragment placed in backstack
        // http://stackoverflow.com/questions/34649126/fragment-back-stack-and-isremoving
        if (isSaved) {
            isSaved = false
            return
        }

        // See https://github.com/Arello-Mobile/Moxy/issues/24
        var anyParentIsRemoving = false
        var parent: Fragment? = fragment.parentFragment
        while (!anyParentIsRemoving && parent != null) {
            anyParentIsRemoving = parent.isRemoving
            parent = parent.parentFragment
        }

        if (fragment.isRemoving || anyParentIsRemoving) {
            graphProvider.invoke().onFeatureFinish()
        }
    }
}