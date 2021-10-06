package ru.malinoile.nasa.model.contracts

import androidx.fragment.app.Fragment

interface FragmentContract {
    fun setFragment(fragment: Fragment, tag: String? = null, addBackStack: Boolean = false)
    fun searchFragment(tag: String): Fragment?
}