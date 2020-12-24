package com.example.madcapstoneproject.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madcapstoneproject.R
import com.example.madcapstoneproject.model.Workout
import kotlinx.android.synthetic.main.fragment_createworkout.view.*
import kotlinx.android.synthetic.main.item_workout.view.*

class WorkoutAdapter (private val workouts:List<Workout>):RecyclerView.Adapter<WorkoutAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun databind(workout:Workout){
            itemView.tvWorkout_itemName.text = workout.title.toString()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_workout,parent,false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(workouts[position])
    }
    override fun getItemCount(): Int {
        return workouts.size
    }
}