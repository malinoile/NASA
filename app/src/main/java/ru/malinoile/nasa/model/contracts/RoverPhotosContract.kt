package ru.malinoile.nasa.model.contracts

import ru.malinoile.nasa.model.entities.RoverPhotoEntity

interface RoverPhotosContract {
    interface View {
        fun renderList(list: List<RoverPhotoEntity>)
        fun renderErrorMessage(msg: String)
        fun setClickable(isClickable: Boolean)
    }
    interface Presenter {
        fun attach(view: View)
        fun onLoadPhotos(date: Long)
        fun detach()
    }
}