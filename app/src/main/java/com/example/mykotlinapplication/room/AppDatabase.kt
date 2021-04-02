package com.example.mykotlinapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mykotlinapplication.room.entities.Story
import com.example.mykotlinapplication.room.entities.StoryDao


@Database(entities = [Story::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun storyDao(): StoryDao

    companion object {
        @Volatile
        var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "AppDatabase.db"
                ).build()
            }
            return INSTANCE as AppDatabase
        }
    }
}