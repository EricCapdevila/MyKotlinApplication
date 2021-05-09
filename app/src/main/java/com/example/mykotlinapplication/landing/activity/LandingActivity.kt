package com.example.mykotlinapplication.landing.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinapplication.R
import com.example.mykotlinapplication.databinding.ActivityLandingBinding
import com.example.mykotlinapplication.landing.adapter.LandingListAdapter
import com.example.mykotlinapplication.landing.viewmodel.StoryViewModel
import com.example.mykotlinapplication.landing.viewmodel.ViewModelFactory
import com.example.mykotlinapplication.repository.Repository
import com.example.mykotlinapplication.room.entities.Story

class LandingActivity: AppCompatActivity()  {

    private lateinit var binding : ActivityLandingBinding
    lateinit var viewModel : StoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_landing)
        viewModel = ViewModelProvider(this, ViewModelFactory(Repository(this)))
            .get(StoryViewModel::class.java)
        viewModel.getStories()
        observeData()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getStories()
    }

    fun observeData(){
        viewModel.storiesLiveData.observe(this, Observer { list ->
            setRecycler(list)
        })
    }

    fun setRecycler(list : List<Story>) {
        binding.landingList.adapter = LandingListAdapter(list, this)
        binding.landingList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false )
        //(binding.landingList.adapter as LandingListAdapter).notifyDataSetChanged()
    }

}