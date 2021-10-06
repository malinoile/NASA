package ru.malinoile.nasa.model.repos

import ru.malinoile.nasa.model.entities.RoverPhotoEntity

interface RoverPhotosRepository {
    fun getRoverPhotoList(
        sol: Int,
        onSuccess: (List<RoverPhotoEntity>) -> Unit,
        onError: (Throwable) -> Unit
    )
}