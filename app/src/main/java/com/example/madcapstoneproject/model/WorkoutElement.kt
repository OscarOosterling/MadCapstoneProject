package com.example.madcapstoneproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workoutElementTable")
data class WorkoutElement(
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "time")
    var time:Int,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Long?=null
)