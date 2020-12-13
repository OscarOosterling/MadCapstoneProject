package com.example.madcapstoneproject.ui

import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madcapstoneproject.R
import com.example.madcapstoneproject.model.Workout
import com.example.madcapstoneproject.model.WorkoutElement
import kotlinx.android.synthetic.main.fragment_createworkout.view.*
import kotlinx.android.synthetic.main.item_workout_element.view.*

class WorkoutElementAdapter (private val workoutElements:List<WorkoutElement>): RecyclerView.Adapter<WorkoutElementAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun databind(workoutElement: WorkoutElement){
            itemView.tvExerciseName.text = workoutElement.title
            itemView.tvExerciseTime.text = workoutElement.time.toString()

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_workout_element,parent,false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(workoutElements[position])
    }
    override fun getItemCount(): Int {
        return workoutElements.size
    }


}