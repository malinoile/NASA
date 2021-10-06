package ru.malinoile.nasa.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.malinoile.nasa.model.entities.PictureOfDayEntity
import ru.malinoile.nasa.ui.fragments.PictureOfDayFragment

class PicturesPagerAdapter(
    fragment: Fragment,
    private var listPictures: List<PictureOfDayEntity> = emptyList()
) : FragmentStateAdapter(fragment) {

    fun setList(list: List<PictureOfDayEntity>) {
        listPictures = list
    }

    override fun getItemCount(): Int = listPictures.size

    override fun createFragment(position: Int): Fragment {
        return PictureOfDayFragment.newInstance(listPictures[position])
    }
}