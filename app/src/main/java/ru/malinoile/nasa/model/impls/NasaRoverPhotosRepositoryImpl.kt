package ru.malinoile.nasa.model.impls

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import ru.malinoile.nasa.BuildConfig
import ru.malinoile.nasa.model.NasaService
import ru.malinoile.nasa.model.entities.NasaRoverPhotoListEntity
import ru.malinoile.nasa.model.entities.RoverPhotoEntity
import ru.malinoile.nasa.model.repos.RoverPhotosRepository

class NasaRoverPhotosRepositoryImpl(retrofit: Retrofit) : RoverPhotosRepository {
    private val service = retrofit.create(NasaService::class.java)

    override fun getRoverPhotoList(
        sol: Int,
        onSuccess: (List<RoverPhotoEntity>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.getRoverPhotos(sol, BuildConfig.NASA_API_KEY).enqueue(object : Callback<NasaRoverPhotoListEntity> {
            override fun onResponse(
                call: Call<NasaRoverPhotoListEntity>,
                response: Response<NasaRoverPhotoListEntity>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it.photos) }
                } else {
                    onError(Throwable())
                }
            }

            override fun onFailure(call: Call<NasaRoverPhotoListEntity>, t: Throwable) {
                onError(t)
            }

        })
    }

}