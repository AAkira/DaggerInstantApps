package com.github.aakira.daggerinstantapps.data.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class PullRequest(
        val id: Int,
        val number: Int,
        val state: String,
        val title: String,
        val body: String,
        @Json(name = "diff_url") val diffUrl: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(number)
        parcel.writeString(state)
        parcel.writeString(title)
        parcel.writeString(body)
        parcel.writeString(diffUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PullRequest> {
        override fun createFromParcel(parcel: Parcel): PullRequest {
            return PullRequest(parcel)
        }

        override fun newArray(size: Int): Array<PullRequest?> {
            return arrayOfNulls(size)
        }
    }
}