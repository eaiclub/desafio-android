package com.example.desafio_android.common.domain.models

import android.os.Parcel
import android.os.Parcelable

data class NasaApod( val copyright : String,
                     val date : String,
                     val explanation : String,
                     val hdUrl : String,
                     val mediaType : String,
                     val serviceVersion : String,
                     val title : String,
                     val url : String): Parcelable {

    var progress: Boolean = false

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
        progress = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(copyright)
        parcel.writeString(date)
        parcel.writeString(explanation)
        parcel.writeString(hdUrl)
        parcel.writeString(mediaType)
        parcel.writeString(serviceVersion)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeByte(if (progress) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NasaApod> {
        override fun createFromParcel(parcel: Parcel): NasaApod {
            return NasaApod(parcel)
        }

        override fun newArray(size: Int): Array<NasaApod?> {
            return arrayOfNulls(size)
        }
    }

}
