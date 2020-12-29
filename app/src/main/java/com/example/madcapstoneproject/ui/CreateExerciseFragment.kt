
package com.example.madcapstoneproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.madcapstoneproject.R
import com.example.madcapstoneproject.databinding.FragmentCreateexerciseBinding
import com.example.madcapstoneproject.model.WorkoutElement
import com.example.madcapstoneproject.model.WorkoutViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
const val REQ_EXERCISE_KEY = "req_exercise"
const val BUNDLE_EXERCISE_KEY = "bundle_exercise"

class CreateExerciseFragment : Fragment() {


    private var _binding:FragmentCreateexerciseBinding?=null
    private val binding get() = _binding!!

    private val viewModel: WorkoutViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateexerciseBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddExercise.setOnClickListener{
            addExercise()
        }
    }
    fun addExercise(){
        val exercise = WorkoutElement(binding.tvCreateExerciseName.text.toString(),binding.tvCreateExerciseTime.text.toString())
        if(binding.tvCreateExerciseName.text.toString() != "" && binding.tvCreateExerciseTime.text.toString() != ""){
            setFragmentResult(REQ_EXERCISE_KEY, bundleOf(Pair(BUNDLE_EXERCISE_KEY,exercise)))
        }
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}