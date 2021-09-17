package ru.malinoile.nasa.model.impls

import android.util.Log
import retrofit2.Retrofit
import ru.malinoile.nasa.model.entities.PictureOfDayEntity
import ru.malinoile.nasa.model.repos.PictureOfDayRepository

class CachePictureOfDayRepositoryImpl(retrofit: Retrofit) : PictureOfDayRepository {
    private var pictureOfDay: PictureOfDayEntity? = null
    private val nasaRepository: PictureOfDayRepository by lazy {
        NasaPictureOfDayRepositoryImpl(retrofit)
    }

    override fun getPictureOfDay(
        onSuccess: (PictureOfDayEntity) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Log.d("@@@", "cache")
        pictureOfDay?.let { onSuccess(it) }
            ?: nasaRepository.getPictureOfDay(
                { picture -> onSuccess(picture) },
                { throwable -> onError(throwable) }
            )
    }

}