package ru.malinoile.nasa.model

import ru.malinoile.nasa.model.entities.PictureOfDayEntity

interface PictureOfDayContract {
    interface View {
        fun renderPicture(picture: PictureOfDayEntity)
        fun renderErrorToast(msg: String)
    }

    interface Presenter {
        fun attach(view: View)
        fun getPictureOfDay()
        fun detach()
    }
}