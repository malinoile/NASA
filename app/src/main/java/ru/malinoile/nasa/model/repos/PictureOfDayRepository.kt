package ru.malinoile.nasa.model.repos

import ru.malinoile.nasa.model.entities.PictureOfDayEntity

interface PictureOfDayRepository {
    fun getPictureOfDay(
        onSuccess: (PictureOfDayEntity) -> Unit,
        onError: (Throwable) -> Unit
    )
}