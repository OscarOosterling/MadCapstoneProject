package com.example.madcapstoneproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcapstoneproject.R
import com.example.madcapstoneproject.model.Workout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_workouts.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class WorkoutFragment : Fragment() {

    private var workouts = arrayListOf<Workout>()
    private var workoutAdapter=WorkoutAdapter(workouts)


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workouts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() {
        rv_workouts.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        rv_workouts.adapter = workoutAdapter
    }
}