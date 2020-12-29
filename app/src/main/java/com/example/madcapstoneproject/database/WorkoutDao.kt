package com.example.madcapstoneproject.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.madcapstoneproject.model.Workout

@Dao
interface WorkoutDao{
    @Query("SELECT * FROM workoutTable")
    fun getAllWorkouts():LiveData<List<Workout>>

    @Insert
    fun insertWorkout(workout: Workout)

    @Delete
    fun deleteWorkout(workout: Workout)

    @Update
    fun updateWorkout(workout: Workout)

    @Query("SELECT * FROM  workoutTable WHERE id IS :id")
    fun getSpecificWorkout(id: String):Workout
}