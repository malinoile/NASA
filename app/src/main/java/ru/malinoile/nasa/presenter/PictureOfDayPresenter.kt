package ru.malinoile.nasa.presenter

import android.util.Log
import androidx.fragment.app.Fragment
import retrofit2.Retrofit
import ru.malinoile.nasa.model.PictureOfDayContract
import ru.malinoile.nasa.model.impls.CachePictureOfDayRepositoryImpl
import ru.malinoile.nasa.model.repos.PictureOfDayRepository

object PictureOfDayPresenter : PictureOfDayContract.Presenter {
    private var view: PictureOfDayContract.View? = null
    private var pictureRepository: PictureOfDayRepository? = null

    operator fun invoke(retrofit: Retrofit): PictureOfDayPresenter {
        if(pictureRepository == null) {
            pictureRepository = CachePictureOfDayRepositoryImpl(retrofit)
        }
        return this
    }

    override fun attach(view: PictureOfDayContract.View) {
        this.view = view
    }

    override fun getPictureOfDay() {
        pictureRepository?.getPictureOfDay({
            view?.renderPicture(it)
        }, {
            view?.renderErrorToast("Error")
        })
    }

    override fun detach() {
        view = null
    }
}