package com.github.aakira.daggerinstantapps.data.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class GithubRepo(
        val id: String,
        val owner: Owner,
        val name: String,
        @Json(name = "full_name") val fullName: String,
        val description: String?,
        @Json(name = "html_url") val htmlUrl: String,
        val url: String,
        @Json(name = "stargazers_count") val stargazersCount: Int

) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readParcelable(Owner::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeParcelable(owner, flags)
        parcel.writeString(name)
        parcel.writeString(fullName)
        parcel.writeString(description)
        parcel.writeString(htmlUrl)
        parcel.writeString(url)
        parcel.writeInt(stargazersCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GithubRepo> {
        override fun createFromParcel(parcel: Parcel): GithubRepo {
            return GithubRepo(parcel)
        }

        override fun newArray(size: Int): Array<GithubRepo?> {
            return arrayOfNulls(size)
        }
    }
}