package com.example.madcapstoneproject.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WorkoutElement(
    var title:String,
    var time: String?,
):Parcelable