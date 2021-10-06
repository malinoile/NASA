package ru.malinoile.nasa.presenter

import android.content.Context
import android.util.Log
import retrofit2.Retrofit
import ru.malinoile.nasa.R
import ru.malinoile.nasa.model.contracts.PictureOfDayContract
import ru.malinoile.nasa.model.impls.CachePictureOfDayRepositoryImpl
import ru.malinoile.nasa.model.repos.PictureOfDayRepository

class PictureOfDayPresenter(private val pictureRepository: PictureOfDayRepository) :
    PictureOfDayContract.Presenter {
    private var view: PictureOfDayContract.View? = null

    override fun attach(view: PictureOfDayContract.View) {
        this.view = view
    }

    override fun getPicturesOfDay(context: Context) {
        pictureRepository.getPicturesOfDay({
            view?.renderPictures(it)
        }, {
            view?.renderErrorMessage(context.getString(R.string.error_msg))
        })
    }

    override fun detach() {
        view = null
    }
}