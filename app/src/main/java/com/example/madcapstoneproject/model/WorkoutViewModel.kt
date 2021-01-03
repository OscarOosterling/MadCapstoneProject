package com.example.madcapstoneproject.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.madcapstoneproject.database.WorkoutRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutViewModel (application: Application):AndroidViewModel(application){
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val workoutRepository = WorkoutRepository(application.applicationContext)

    val workouts:LiveData<List<Workout>> = workoutRepository.getAllWorkouts()


    fun insertWorkout(workout: Workout){
        ioScope.launch { workoutRepository.insertWorkout(workout) }
    }

    fun deleteWorkout(workout: Workout){
        ioScope.launch { workoutRepository.deleteWorkout(workout) }
    }

    fun updateWorkout(workout: Workout){
        ioScope.launch { workoutRepository.updateWorkout(workout) }
    }

     fun getWorkout(id:String):Workout{
       return  workoutRepository.getSpecificWorkout(id)
    }

}