package com.example.madcapstoneproject.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "workoutTable")
@Parcelize
data class Workout(
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "exercises")
    var exercises:List<WorkoutElement>,
    @ColumnInfo(name = "rounds")
    var rounds:String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Long?=null
):Parcelable