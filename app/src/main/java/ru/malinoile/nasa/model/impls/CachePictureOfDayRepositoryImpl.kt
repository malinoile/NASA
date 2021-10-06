package ru.malinoile.nasa.model.impls

import android.util.Log
import retrofit2.Retrofit
import ru.malinoile.nasa.model.entities.PictureOfDayEntity
import ru.malinoile.nasa.model.repos.PictureOfDayRepository

class CachePictureOfDayRepositoryImpl(retrofit: Retrofit) : PictureOfDayRepository {
    private var listPictures: List<PictureOfDayEntity>? = null
    private val nasaRepository: PictureOfDayRepository by lazy {
        NasaPictureOfDayRepositoryImpl(retrofit)
    }

    override fun getPicturesOfDay(
        onSuccess: (List<PictureOfDayEntity>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        listPictures?.let { onSuccess(it) }
            ?: nasaRepository.getPicturesOfDay(
                { list ->
                    listPictures = list
                    onSuccess(list)
                },
                { throwable -> onError(throwable) }
            )
    }

}