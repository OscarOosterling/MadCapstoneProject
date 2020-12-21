package com.example.madcapstoneproject.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.madcapstoneproject.model.Workout

@Dao
interface WorkoutDao{
    @Query("SELECT * FROM workoutTable")
    fun getAllWorkouts():List<Workout>

    @Insert
    fun insertWorkout(workout: Workout)

    @Delete
    fun deleteWorkout(workout: Workout)

}