package com.example.madcapstoneproject.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.madcapstoneproject.model.Workout
import com.example.madcapstoneproject.model.WorkoutElement


@Database(entities = [WorkoutElement::class], version = 1, exportSchema = false)
abstract class WorkoutElementRoomDatabase : RoomDatabase() {

    abstract fun workoutElementDao(): WorkoutElementDao

    companion object {
        private const val DATABASE_NAME = "WORKOUT_DATABASE"

        @Volatile
        private var workoutElementRoomDatabaseInstance: WorkoutElementRoomDatabase? = null

        fun getDatabase(context: Context): WorkoutElementRoomDatabase? {
            if (workoutElementRoomDatabaseInstance == null) {
                synchronized(WorkoutElementRoomDatabase::class.java) {
                    if (workoutElementRoomDatabaseInstance == null) {
                        workoutElementRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            WorkoutElementRoomDatabase::class.java, DATABASE_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return workoutElementRoomDatabaseInstance
        }
    }
}
