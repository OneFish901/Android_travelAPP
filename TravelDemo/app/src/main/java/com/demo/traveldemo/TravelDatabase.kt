package com.demo.traveldemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Review::class], version = 1)
abstract class TravelDatabase : RoomDatabase() {
    abstract fun commentDao(): ReviewDao

    companion object {
        @Volatile
        private var INSTANCE: TravelDatabase? = null

        fun getDatabase(context: Context): TravelDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TravelDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
