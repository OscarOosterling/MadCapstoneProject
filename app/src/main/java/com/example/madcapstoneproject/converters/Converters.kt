package com.example.madcapstoneproject.converters

import androidx.room.TypeConverter
import com.example.madcapstoneproject.model.WorkoutElement
import com.google.gson.Gson

class Converters{

    @TypeConverter
    fun listToJson(value: List<WorkoutElement>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<WorkoutElement>::class.java).toList()
}