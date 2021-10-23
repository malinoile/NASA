package ru.malinoile.nasa.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.malinoile.nasa.model.entities.NasaRoverPhotoListEntity
import ru.malinoile.nasa.model.entities.PictureOfDayEntity
import ru.malinoile.nasa.model.entities.RoverPhotoEntity

interface NasaService {
    @GET("planetary/apod?thumbs=true")
    fun getPictures(
        @Query("start_date") startDate: String,
        @Query("api_key") apiKey: String
    ): Call<List<PictureOfDayEntity>>

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    fun getRoverPhotos(
        @Query("earth_date") date: String,
        @Query("api_key") apiKey: String
    ): Call<NasaRoverPhotoListEntity>
}