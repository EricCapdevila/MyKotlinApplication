package com.example.mykotlinapplication.landing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mykotlinapplication.repository.Repository

class ViewModelFactory(
    private val repository : Repository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewStoryViewModel::class.java)) {
            return NewStoryViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(StoryViewModel::class.java)){
            return StoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
