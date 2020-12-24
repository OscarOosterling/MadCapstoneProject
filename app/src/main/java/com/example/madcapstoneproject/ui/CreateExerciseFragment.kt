
package com.example.madcapstoneproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.madcapstoneproject.R
import com.example.madcapstoneproject.databinding.FragmentCreateexerciseBinding
import com.example.madcapstoneproject.model.WorkoutViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
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
        
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}