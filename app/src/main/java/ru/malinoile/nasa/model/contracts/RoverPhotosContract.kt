package ru.malinoile.nasa.model.contracts

import ru.malinoile.nasa.model.entities.RoverPhotoEntity

interface RoverPhotosContract {
    interface View {
        fun renderList(list: List<RoverPhotoEntity>)
        fun renderErrorMessage(msg: String)
        fun setFocusable(isFocusable: Boolean)
    }
    interface Presenter {
        fun attach(view: View)
        fun onLoadPhotos(sol: Int)
        fun detach()
    }
}