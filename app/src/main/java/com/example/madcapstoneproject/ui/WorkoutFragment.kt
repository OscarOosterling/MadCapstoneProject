package com.example.madcapstoneproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcapstoneproject.R
import com.example.madcapstoneproject.database.WorkoutRepository
import com.example.madcapstoneproject.databinding.ActivityMainBinding
import com.example.madcapstoneproject.model.Workout
import com.example.madcapstoneproject.model.WorkoutViewModel
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

    private val viewModel:WorkoutViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workouts, container, false)

    }

    private fun observeAddWorkoutResult(){
        viewModel.workouts.observe(viewLifecycleOwner, Observer { workouts ->
            this@WorkoutFragment.workouts.clear()
            this@WorkoutFragment.workouts.addAll(workouts)
            workoutAdapter.notifyDataSetChanged()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeAddWorkoutResult()

    }



    private fun initViews() {

        rv_workouts.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        rv_workouts.adapter = workoutAdapter
    }
}