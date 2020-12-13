package com.example.madcapstoneproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workoutTable")
data class Workout(
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "exercises")
    var exercises:List<WorkoutElement>,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Long?=null
)