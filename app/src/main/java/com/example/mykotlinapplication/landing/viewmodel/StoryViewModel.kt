package com.example.mykotlinapplication.landing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykotlinapplication.repository.Repository
import com.example.mykotlinapplication.room.entities.Story
import kotlinx.coroutines.launch


class StoryViewModel( val repository: Repository) : ViewModel() {

     val storiesLiveData: MutableLiveData<List<Story>> = MutableLiveData()


    //this is what will be observed
    fun getStories() {
        viewModelScope.launch {
            storiesLiveData.value = repository.getStories()
        }
    }

    fun deleteStory(story: Story){
        viewModelScope.launch {
            repository.deleteStory( story)
        }
    }

    fun deleteStoryWithTitle(title: String) {
        viewModelScope.launch{
            repository.deleteStoryByTitle(title)
        }
    }
}
