package ru.malinoile.nasa

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.malinoile.nasa.model.impls.CachePictureOfDayRepositoryImpl
import ru.malinoile.nasa.model.impls.NasaRoverPhotosRepositoryImpl

class MyApplication: Application() {

    companion object {
        private const val BASE_URL = "https://api.nasa.gov/"
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val pictureRepository by lazy {
        CachePictureOfDayRepositoryImpl(retrofit)
    }

    val roverPhotosRepository by lazy {
        NasaRoverPhotosRepositoryImpl(retrofit)
    }

}