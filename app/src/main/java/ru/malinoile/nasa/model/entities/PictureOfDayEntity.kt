package ru.malinoile.nasa.model.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

private const val VIDEO_TYPE = "video"
private const val PHOTO_TYPE = "image"
data class PictureOfDayEntity(
    @SerializedName("title")
    val title: String,
    @SerializedName("explanation")
    val description: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("hdurl")
    val hdUrl: String?,
    @SerializedName("date")
    val date: String,
    @SerializedName("copyright")
    val author: String,
    @SerializedName("media_type")
    val type: String
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    fun getMediaType(): MediaType {
        return when(type) {
            VIDEO_TYPE -> MediaType.VIDEO
            PHOTO_TYPE -> MediaType.PHOTO
            else -> {
                throw Throwable("Получен неопознанный тип фотографии дня")
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(hdUrl)
        parcel.writeString(date)
        parcel.writeString(author)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PictureOfDayEntity> {
        override fun createFromParcel(parcel: Parcel): PictureOfDayEntity {
            return PictureOfDayEntity(parcel)
        }

        override fun newArray(size: Int): Array<PictureOfDayEntity?> {
            return arrayOfNulls(size)
        }
    }
}