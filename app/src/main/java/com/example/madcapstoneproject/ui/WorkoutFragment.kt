package com.example.madcapstoneproject.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcapstoneproject.R
import com.example.madcapstoneproject.database.WorkoutRepository
import com.example.madcapstoneproject.databinding.FragmentCreateworkoutBinding
import com.example.madcapstoneproject.databinding.FragmentWorkoutsBinding
import com.example.madcapstoneproject.model.Workout
import com.example.madcapstoneproject.model.WorkoutElement
import com.example.madcapstoneproject.model.WorkoutViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_workouts.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class WorkoutFragment : Fragment() {


    private var workouts = arrayListOf<Workout>()
    private var workoutAdapter=WorkoutAdapter(workouts){
        workout: Workout -> workoutItemClicked(workout)
    }

    private val viewModel:WorkoutViewModel by viewModels()

    private var _binding: FragmentWorkoutsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWorkoutsBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }
    private fun workoutItemClicked(workout:Workout){

            val selectedWorkout = viewModel.getWorkout(workout.id.toString())
            workoutID = workout.id.toString()
            requireActivity().title = selectedWorkout.title
            setFragmentResult(REQ_WORKOUT_KEY, bundleOf(Pair(BUNDLE_WORKOUT_KEY,selectedWorkout)))

        findNavController().navigate(R.id.action_workoutFragment_to_startOrEditFragment)
        editMode = true;
    }

    //Gets called when fragment is shown, looks if any new workouts have been added.
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
        requireActivity().title = getString(R.string.fragmentworkouttitle)
    }



    private fun initViews() {

        binding.rvWorkouts.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        binding.rvWorkouts.adapter = workoutAdapter
        createItemTouchHelper().attachToRecyclerView(binding.rvWorkouts)
    }
    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                Collections.swap(workouts,fromPosition,toPosition)
                workoutAdapter.notifyItemMoved(fromPosition,toPosition)
                return true
            }

            override fun isLongPressDragEnabled(): Boolean {
                return true
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteWorkout(workouts[position])
                workouts.removeAt(position)
                workoutAdapter.notifyDataSetChanged()
            }

        }
        return ItemTouchHelper(callback)
    }
}