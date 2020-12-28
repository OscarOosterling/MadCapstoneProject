package com.example.madcapstoneproject.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcapstoneproject.databinding.FragmentCreateworkoutBinding
import com.example.madcapstoneproject.model.Workout
import com.example.madcapstoneproject.model.WorkoutElement
import com.example.madcapstoneproject.model.WorkoutViewModel
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CreateWorkoutFragment : Fragment() {

    private val viewModel:WorkoutViewModel by viewModels()

    private var _binding: FragmentCreateworkoutBinding? = null
    private val binding get() = _binding!!

    private var exercises = arrayListOf<WorkoutElement>()
    private var exerciseAdapter=WorkoutElementAdapter(exercises)




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
        initViews()
        binding.btnAddWorkout.setOnClickListener{
            addWorkout()
        }
        observeAddExerciseResult()

    }
    private fun initViews(){
        binding.rvWorkoutItems.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        binding.rvWorkoutItems.adapter = exerciseAdapter
        createItemTouchHelper().attachToRecyclerView(binding.rvWorkoutItems)
    }

    private fun addWorkout() {
        if(binding.tvWorkoutname.text.toString() !=""){
            viewModel.insertWorkout(workout = Workout(
                title = binding.tvWorkoutname.text.toString(),
                exercises = exercises))
        }
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeAddExerciseResult(){
        setFragmentResultListener(REQ_EXERCISE_KEY){
                key,bundle->bundle.getParcelable<WorkoutElement>(BUNDLE_EXERCISE_KEY)?.let{
            val exercise = WorkoutElement(it.title,it.time)
            exercises.add(exercise)
            exerciseAdapter.notifyDataSetChanged()
        }?: Log.e("exerciseFragment","Request triggered,but empty exercise text")
        }
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
                Collections.swap(exercises,fromPosition,toPosition)
                exerciseAdapter.notifyItemMoved(fromPosition,toPosition)
                return true
            }

            override fun isLongPressDragEnabled(): Boolean {
                return true
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                exercises.removeAt(position)
                exerciseAdapter.notifyDataSetChanged()
            }

        }
        return ItemTouchHelper(callback)
    }
}