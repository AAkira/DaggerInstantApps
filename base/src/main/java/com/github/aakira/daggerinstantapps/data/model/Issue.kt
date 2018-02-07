package com.github.aakira.daggerinstantapps.data.model

import android.os.Parcel
import android.os.Parcelable

data class Issue(
        val id: Int,
        val state: String,
        val title: String,
        val body: String,
        val labels: List<Label>
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.createTypedArrayList(Label))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(state)
        parcel.writeString(title)
        parcel.writeString(body)
        parcel.writeTypedList(labels)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Issue> {
        override fun createFromParcel(parcel: Parcel): Issue {
            return Issue(parcel)
        }

        override fun newArray(size: Int): Array<Issue?> {
            return arrayOfNulls(size)
        }
    }
}