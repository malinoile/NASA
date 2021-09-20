package ru.malinoile.nasa.model.impls

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import ru.malinoile.nasa.model.NasaService
import ru.malinoile.nasa.model.entities.PictureOfDayEntity
import ru.malinoile.nasa.model.repos.PictureOfDayRepository

class NasaPictureOfDayRepositoryImpl(retrofit: Retrofit) : PictureOfDayRepository {
    private val service = retrofit.create(NasaService::class.java)
    override fun getPictureOfDay(
        onSuccess: (PictureOfDayEntity) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.getPicture().enqueue(object : Callback<PictureOfDayEntity> {
            override fun onResponse(
                call: Call<PictureOfDayEntity>,
                response: Response<PictureOfDayEntity>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<PictureOfDayEntity>, t: Throwable) {
                Log.d("@@@", "onFailure")
                onError(t)
            }
        })
    }


}