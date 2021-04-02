package com.example.mykotlinapplication.landing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mykotlinapplication.repository.Repository
import com.example.mykotlinapplication.room.entities.Story


class NewStoryViewModel(repository: Repository) : ViewModel() {
    private val storyLiveData: MutableLiveData<Story> = MutableLiveData()
    private var repository : Repository = repository

    //this is what will be observed
    fun getStory() {
        repository.getStories()
    }

    fun setStory(model : Story){gi
        repository.insertStory(model)
    }

    fun deleteStory(model: Story){
        repository.deleteStory(model)
    }
}