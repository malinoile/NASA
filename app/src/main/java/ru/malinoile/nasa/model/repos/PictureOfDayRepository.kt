package ru.malinoile.nasa.model.repos

import ru.malinoile.nasa.model.entities.PictureOfDayEntity

interface PictureOfDayRepository {
    fun getPicturesOfDay(
        onSuccess: (List<PictureOfDayEntity>) -> Unit,
        onError: (Throwable) -> Unit
    )
}