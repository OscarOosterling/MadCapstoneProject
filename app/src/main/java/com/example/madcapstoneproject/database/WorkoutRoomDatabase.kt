package com.example.madcapstoneproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.madcapstoneproject.model.Workout


@Database(entities = [Workout::class], version = 1, exportSchema = false)
abstract class WorkoutRoomDatabase : RoomDatabase() {

    abstract fun workoutDao(): WorkoutDao

    companion object {
        private const val DATABASE_NAME = "WORKOUT_DATABASE"

        @Volatile
        private var workoutRoomDatabaseInstance: WorkoutRoomDatabase? = null

        fun getDatabase(context: Context): WorkoutRoomDatabase? {
            if (workoutRoomDatabaseInstance == null) {
                synchronized(WorkoutRoomDatabase::class.java) {
                    if (workoutRoomDatabaseInstance == null) {
                        workoutRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            WorkoutRoomDatabase::class.java, DATABASE_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return workoutRoomDatabaseInstance
        }

    }
}
