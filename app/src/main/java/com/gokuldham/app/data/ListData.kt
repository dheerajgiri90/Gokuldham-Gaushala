package com.gokuldham.app.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListData(
    val Name: String,
    val Id: Int,
) : Parcelable