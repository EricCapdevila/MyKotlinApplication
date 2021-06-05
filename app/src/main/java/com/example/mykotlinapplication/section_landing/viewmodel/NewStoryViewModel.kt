package com.example.mykotlinapplication.section_landing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykotlinapplication.repository.Repository
import com.example.mykotlinapplication.room.entities.Story
import kotlinx.coroutines.launch


class NewStoryViewModel(repository: Repository) : ViewModel() {
    private var repository : Repository = repository

    lateinit var title: String
    lateinit var abstract: String

    fun setStory(){
        viewModelScope.launch {
            repository.insertStory(Story(title, abstract))
        }
    }

}
