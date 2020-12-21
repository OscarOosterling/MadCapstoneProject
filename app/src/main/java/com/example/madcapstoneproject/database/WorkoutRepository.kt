package com.example.madcapstoneproject.database

import android.content.Context
import com.example.madcapstoneproject.model.Workout
import com.example.madcapstoneproject.database.*

public class WorkoutRepository (context: Context){
    private var workoutDao:WorkoutDao

    init {
        val workoutRoomDatabase = WorkoutRoomDatabase.getDatabase(context)
        workoutDao = workoutRoomDatabase!!.workoutDao()
    }

    fun getAllWorkouts():List<Workout>{
        return workoutDao.getAllWorkouts()
    }

    fun insertWorkout(workout: Workout){
        workoutDao.insertWorkout(workout)
    }

    fun deleteWorkout(workout: Workout){
        workoutDao.deleteWorkout(workout)
    }
}