package ru.malinoile.nasa.model.entities

import com.google.gson.annotations.SerializedName

data class NasaRoverPhotoListEntity(
    @SerializedName("photos")
    val photos: List<RoverPhotoEntity>
)