package com.example.madcapstoneproject.ui

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.madcapstoneproject.R
import com.example.madcapstoneproject.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(findViewById(R.id.toolbar))
        navController = findNavController(R.id.nav_host_fragment)

        fabToggler()
    }
    private fun fabToggler() {
        navController.addOnDestinationChangedListener{_,destination,_->
            if(destination.id in arrayOf(R.id.workoutFragment)) {
                binding.fab.setOnClickListener{
                    navController.navigate(R.id.action_workoutFragment_to_createWorkoutFragment)
                }
            }
            if(destination.id in arrayOf(R.id.createWorkoutFragment)) {
                binding.fab.setOnClickListener{
                    navController.navigate(R.id.action_createWorkoutFragment_to_createExerciseFragment)
                }
            }
            if(destination.id in arrayOf(R.id.createExerciseFragment)) {
                binding.fab.setOnClickListener{
                    navController.navigate(R.id.action_createExerciseFragment_to_createWorkoutFragment)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}