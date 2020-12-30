package com.example.madcapstoneproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.madcapstoneproject.R
import com.example.madcapstoneproject.databinding.FragmentCreateworkoutBinding
import com.example.madcapstoneproject.databinding.FragmentStartoreditBinding

class StartOrEditFragment:Fragment() {
    private var _binding: FragmentStartoreditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartoreditBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStartWorkout.setOnClickListener{
            findNavController().navigate(R.id.action_startOrEditFragment_to_workoutActivityFragment)
        }
        binding.btnEditWorkout.setOnClickListener {
            findNavController().navigate(R.id.action_startOrEditFragment_to_createWorkoutFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}