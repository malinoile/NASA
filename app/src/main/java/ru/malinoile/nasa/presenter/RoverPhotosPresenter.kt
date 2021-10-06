package ru.malinoile.nasa.presenter

import ru.malinoile.nasa.model.contracts.RoverPhotosContract
import ru.malinoile.nasa.model.repos.RoverPhotosRepository

class RoverPhotosPresenter(private val repository: RoverPhotosRepository) :
    RoverPhotosContract.Presenter {
    private var view: RoverPhotosContract.View? = null

    override fun attach(view: RoverPhotosContract.View) {
        this.view = view
    }

    override fun onLoadPhotos(sol: Int) {
        view?.setFocusable(false)
        repository.getRoverPhotoList(sol, {
            view?.apply {
                renderList(it)
                setFocusable(true)
            }
        }, {
            view?.apply {
                renderErrorMessage(it.message.toString())
                setFocusable(true)
            }
        })
    }

    override fun detach() {
        view = null
    }
}