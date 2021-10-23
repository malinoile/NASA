package ru.malinoile.nasa.presenter

import android.annotation.SuppressLint
import ru.malinoile.nasa.model.contracts.RoverPhotosContract
import ru.malinoile.nasa.model.repos.RoverPhotosRepository
import java.text.SimpleDateFormat
import java.util.*

class RoverPhotosPresenter(private val repository: RoverPhotosRepository) :
    RoverPhotosContract.Presenter {
    private var view: RoverPhotosContract.View? = null

    override fun attach(view: RoverPhotosContract.View) {
        this.view = view
    }

    @SuppressLint("SimpleDateFormat")
    override fun onLoadPhotos(date: Long) {
        view?.setClickable(false)
        val currentDate = SimpleDateFormat("yyyy-MM-dd").format(Date(date))
        repository.getRoverPhotoList(currentDate, {
            view?.apply {
                renderList(it)
                setClickable(true)
            }
        }, {
            view?.apply {
                renderErrorMessage(it.message.toString())
                setClickable(true)
            }
        })
    }

    override fun detach() {
        view = null
    }
}