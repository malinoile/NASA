package ru.malinoile.nasa.model.impls

import android.annotation.SuppressLint
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import ru.malinoile.nasa.BuildConfig
import ru.malinoile.nasa.model.NasaService
import ru.malinoile.nasa.model.entities.PictureOfDayEntity
import ru.malinoile.nasa.model.repos.PictureOfDayRepository
import java.text.SimpleDateFormat
import java.util.*

class NasaPictureOfDayRepositoryImpl(retrofit: Retrofit) : PictureOfDayRepository {
    private val service = retrofit.create(NasaService::class.java)
    override fun getPicturesOfDay(
        onSuccess: (List<PictureOfDayEntity>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.getPictures(getStartStringDate(), BuildConfig.NASA_API_KEY).enqueue(object : Callback<List<PictureOfDayEntity>> {
            override fun onResponse(
                call: Call<List<PictureOfDayEntity>>,
                response: Response<List<PictureOfDayEntity>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<PictureOfDayEntity>>, t: Throwable) {
                onError(t)
            }
        })
    }

    @SuppressLint("SimpleDateFormat")
    private fun getStartStringDate(): String {
        return SimpleDateFormat("yyyy-MM-dd").format(getStartDate())
    }

    private fun getStartDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -2)

        return calendar.time
    }


}