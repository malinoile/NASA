package ru.malinoile.nasa.model.contracts

import android.content.Context
import ru.malinoile.nasa.model.entities.PictureOfDayEntity

interface PictureOfDayContract {
    interface View {
        fun renderPictures(list: List<PictureOfDayEntity>)
        fun renderErrorMessage(msg: String)
    }

    interface Presenter {
        fun attach(view: View)
        fun getPicturesOfDay(context: Context)
        fun detach()
    }
}