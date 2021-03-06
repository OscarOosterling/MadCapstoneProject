package com.example.madcapstoneproject.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madcapstoneproject.model.Workout
import com.example.madcapstoneproject.database.*
public class WorkoutRepository (context: Context){
    private var workoutDao:WorkoutDao

    init {
        val workoutRoomDatabase = WorkoutRoomDatabase.getDatabase(context)
        workoutDao = workoutRoomDatabase!!.workoutDao()
    }

    fun getAllWorkouts():LiveData<List<Workout>>{
        return workoutDao.getAllWorkouts()?:MutableLiveData(emptyList())
    }

    suspend fun insertWorkout(workout: Workout){
        workoutDao.insertWorkout(workout)
    }

    suspend fun deleteWorkout(workout: Workout){
        workoutDao.deleteWorkout(workout)
    }

    suspend fun updateWorkout(workout: Workout){
        workoutDao.updateWorkout(workout)
    }

      fun getSpecificWorkout(id:String):Workout{
        return workoutDao.getSpecificWorkout(id)
    }
}