package com.example.madcapstoneproject.database


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.madcapstoneproject.model.WorkoutElement

@Dao
interface WorkoutElementDao{
    @Query("SELECT * FROM workoutTable")
    fun getAllWorkouts():List<WorkoutElement>

    @Insert
    fun insertWorkout(workout: WorkoutElement)

    @Delete
    fun deleteWorkout(workout: WorkoutElement)

}