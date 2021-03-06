package com.example.madcapstoneproject.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.madcapstoneproject.R
import com.example.madcapstoneproject.databinding.FragmentActivityworkoutBinding
import com.example.madcapstoneproject.model.Workout
import com.example.madcapstoneproject.model.WorkoutElement
import java.lang.Math.ceil

const val REQ_WORKOUT_KEY = "req_workout"
const val BUNDLE_WORKOUT_KEY = "bundle_workout"

class WorkoutActivityFragment : Fragment() {


    private var _binding: FragmentActivityworkoutBinding?=null
    private val binding get() = _binding!!

    lateinit var countDownTimer:CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActivityworkoutBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeGetWorkoutResult()
    }
    //Gets selected workout from Workoutfragment
    private fun observeGetWorkoutResult(){

        setFragmentResultListener(REQ_WORKOUT_KEY){
                key,bundle->bundle.getParcelable<Workout>(BUNDLE_WORKOUT_KEY)?.let{
                   var workout = Workout(it.title,it.exercises,it.rounds)
            initView(workout)
        }?: Log.e("exerciseFragment","Request triggered,but empty exercise text")
        }
    }

    private fun initView(workout: Workout){
        binding.tvActivityTitle.text = workout.exercises[0].title
        binding.tvActivityNext.text = workout.exercises[1].title
        binding.tvActivityTime.text = workout.exercises[0].time
        binding.tvActivityRound.text = "Round: 1 / "+workout.rounds
        startCountDown(workout,0,1)
    }


    //Starts the workout, and initiates the countdown
    private fun startCountDown(workout: Workout,exerciseNumber:Int,roundNumber:Int){
        var time = workout.exercises[exerciseNumber].time?.toLong()!!*1000
        countDownTimer = object:CountDownTimer(time,1000){
            override fun onFinish() {
                setNextRound(workout,exerciseNumber+1,roundNumber)
            }

            override fun onTick(p0: Long) {
                binding.tvActivityTime.text = (p0/1000).toString()
            }
        }
        countDownTimer.start()
    }

    //Gets called when countdown is finished, Checks if current exercise is last exercise and if current round is last round
    // When it is not it will start a new timer with updated exercise names and rounds
    private fun setNextRound(
        workout: Workout,
        exerciseNumber: Int,
        roundNumber: Int
    ) {
        if(workout.exercises.size<=exerciseNumber){
            if(workout.rounds.toInt()<=roundNumber){
                //END OF WORKOUT
                findNavController().navigate(R.id.action_workoutActivityFragment_to_workoutFragment)
            }else{
                //END OF ROUND
                binding.tvActivityRound.text = getString(R.string.activityroundtext)+(roundNumber.toInt()+1).toString()+getString(
                    R.string.dashbetweenroundtext)+workout.rounds
                binding.tvActivityTitle.text = workout.exercises[0].title
                binding.tvActivityNext.text = workout.exercises[1].title
                startCountDown(workout,0,roundNumber+1)
            }
        }else{
            //NEXT EXERCISE
            binding.tvActivityTitle.setText(workout.exercises[exerciseNumber].title)
            if(workout.exercises.size<=exerciseNumber+1){
                if(workout.rounds.toInt()<=roundNumber){
                    binding.tvActivityNext.setText("")
                }else{
                    binding.tvActivityNext.setText(workout.exercises[0].title)
                }
            }else{
                binding.tvActivityNext.setText(workout.exercises[exerciseNumber+1].title)
            }
            startCountDown(workout,exerciseNumber,roundNumber)
        }

    }

}