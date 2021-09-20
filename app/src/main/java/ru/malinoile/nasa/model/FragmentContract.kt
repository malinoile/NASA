package ru.malinoile.nasa.model

import androidx.fragment.app.Fragment

interface FragmentContract {
    fun setFragment(fragment: Fragment, tag: String? = null, addBackStack: Boolean = false)
}