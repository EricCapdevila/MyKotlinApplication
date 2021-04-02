package com.example.mykotlinapplication.repository

import android.content.Context
import com.example.mykotlinapplication.room.AppDatabase
import com.example.mykotlinapplication.room.entities.Story

class Repository (context: Context){

    var storyDao = AppDatabase.getInstance(context).storyDao()

    fun getStories() : List<Story>{
        return storyDao.getAll()
    }

    fun insertStory(story: Story){
        storyDao.insertAll(story)
    }

    fun deleteStory(story: Story){
        storyDao.delete(story)
    }
}