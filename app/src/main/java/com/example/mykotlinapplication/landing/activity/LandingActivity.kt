package com.example.mykotlinapplication.landing.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mykotlinapplication.R
import com.example.mykotlinapplication.databinding.ActivityLandingBinding
import com.example.mykotlinapplication.landing.viewmodel.StoryViewModel
import com.example.mykotlinapplication.landing.viewmodel.ViewModelFactory
import com.example.mykotlinapplication.repository.Repository
import com.example.mykotlinapplication.room.entities.Story

class LandingActivity: AppCompatActivity()  {

    private lateinit var binding : ActivityLandingBinding
    private lateinit var viewModel : StoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_landing)
        viewModel = ViewModelProvider(this, ViewModelFactory(Repository(this)))
            .get(StoryViewModel::class.java)
        viewModel.getStories()
        observeData()
        setButtonListeners()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getStories()
    }

    fun observeData(){
        viewModel.storiesLiveData.observe(this, Observer { list ->
            println("this is the result of stories")
            println(list)
        })
    }

    fun setButtonListeners(){
        binding.buttonNew.setOnClickListener {
            startActivity(Intent(this, NewStoryActivity::class.java))
        }
    }
}