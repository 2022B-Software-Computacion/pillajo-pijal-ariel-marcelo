package com.example.deber1_recyclerview

import android.os.Parcel
import android.os.Parcelable

class Setting (
    var title: String?,
    var description: String?,
    ) : Parcelable {

    constructor(parcel: Parcel): this(
        parcel.readString(),
        parcel.readString()
    ){

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Setting> {
        override fun createFromParcel(parcel: Parcel): Setting {
            return Setting(parcel)
        }

        override fun newArray(size: Int): Array<Setting?> {
            return arrayOfNulls(size)
        }
    }
}