package ru.malinoile.nasa.model.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class RoverPhotoEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("img_src")
    val url: String?,
    @SerializedName("earth_date")
    val date: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(url)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RoverPhotoEntity> {
        override fun createFromParcel(parcel: Parcel): RoverPhotoEntity {
            return RoverPhotoEntity(parcel)
        }

        override fun newArray(size: Int): Array<RoverPhotoEntity?> {
            return arrayOfNulls(size)
        }
    }

}