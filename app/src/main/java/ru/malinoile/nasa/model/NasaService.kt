package ru.malinoile.nasa.model

import retrofit2.Call
import retrofit2.http.GET
import ru.malinoile.nasa.model.entities.PictureOfDayEntity

private const val PICTURE_OF_DAY_SUB_URL = "planetary/apod"
private const val API_KEY = "cKMqOEsfo8mB4KPaRe6mWf4LXadS44jjSpHU4QiU"
interface NasaService {
    @GET("$PICTURE_OF_DAY_SUB_URL?api_key=$API_KEY")
    fun getPicture(): Call<PictureOfDayEntity>
}