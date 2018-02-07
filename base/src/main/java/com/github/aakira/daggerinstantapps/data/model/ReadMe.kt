package com.github.aakira.daggerinstantapps.data.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class ReadMe(
        val path: String,
        @Json(name = "html_url") val htmlUrl: String,
        @Json(name = "download_url") val downLoadUrl: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(path)
        parcel.writeString(htmlUrl)
        parcel.writeString(downLoadUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReadMe> {
        override fun createFromParcel(parcel: Parcel): ReadMe {
            return ReadMe(parcel)
        }

        override fun newArray(size: Int): Array<ReadMe?> {
            return arrayOfNulls(size)
        }
    }
}