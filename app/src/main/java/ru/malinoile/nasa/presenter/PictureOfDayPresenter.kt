package ru.malinoile.nasa.presenter

import android.util.Log
import retrofit2.Retrofit
import ru.malinoile.nasa.model.PictureOfDayContract
import ru.malinoile.nasa.model.impls.CachePictureOfDayRepositoryImpl
import ru.malinoile.nasa.model.repos.PictureOfDayRepository

class PictureOfDayPresenter(retrofit: Retrofit) : PictureOfDayContract.Presenter {
    private var view: PictureOfDayContract.View? = null
    private val pictureRepository: PictureOfDayRepository by lazy {
        CachePictureOfDayRepositoryImpl(retrofit)
    }

    override fun attach(view: PictureOfDayContract.View) {
        this.view = view
    }

    override fun getPictureOfDay() {
        pictureRepository.getPictureOfDay({
            view?.renderPicture(it)
        }, {
            view?.renderErrorToast("Error")
        })
    }

    override fun detach() {
        view = null
    }
}