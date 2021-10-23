package ru.malinoile.nasa.model.repos

import ru.malinoile.nasa.model.entities.RoverPhotoEntity

interface RoverPhotosRepository {
    fun getRoverPhotoList(
        date: String,
        onSuccess: (List<RoverPhotoEntity>) -> Unit,
        onError: (Throwable) -> Unit
    )
}