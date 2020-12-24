package com.example.madcapstoneproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.madcapstoneproject.R
import com.example.madcapstoneproject.databinding.FragmentCreateworkoutBinding
import com.example.madcapstoneproject.model.Workout
import com.example.madcapstoneproject.model.WorkoutElement
import com.example.madcapstoneproject.model.WorkoutViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CreateWorkoutFragment : Fragment() {

    private val viewModel:WorkoutViewModel by viewModels()

    private var _binding: FragmentCreateworkoutBinding? = null
    private val binding get() = _binding!!

    public var exercises = arrayListOf<WorkoutElement>()
    private var workoutAdapter=WorkoutElementAdapter(exercises)


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateworkoutBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddWorkout.setOnClickListener{
            addWorkout()
        }

    }

    private fun addWorkout() {
        viewModel.insertWorkout(workout = Workout(
            title = binding.tvWorkoutname.text.toString(),
            exercises = exercises))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}