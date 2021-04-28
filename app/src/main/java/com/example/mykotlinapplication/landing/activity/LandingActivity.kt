package com.example.mykotlinapplication.landing.activity

import android.content.Intent
import android.icu.lang.UCharacter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinapplication.R
import com.example.mykotlinapplication.databinding.ActivityLandingBinding
import com.example.mykotlinapplication.landing.adapter.LandingListAdapter
import com.example.mykotlinapplication.landing.adapter.TestAdapter2
import com.example.mykotlinapplication.landing.viewmodel.StoryViewModel
import com.example.mykotlinapplication.landing.viewmodel.ViewModelFactory
import com.example.mykotlinapplication.repository.Repository
import com.example.mykotlinapplication.room.entities.Story
import java.util.*

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
        //setTestRecycler()
        //setRecycler( listOf(Story("a", "a"), Story("b", "b"), Story("c", "c"), Story("d", "c")))
        observeData()
    }

    override fun onResume() {
        super.onResume()
        //viewModel.getStories()
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

    fun setTestRecycler(){
        binding.landingList.adapter = TestAdapter2(listOf("a", "b", "c", "d", "e"), this)
        binding.landingList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false )
        (binding.landingList.adapter as TestAdapter2).notifyDataSetChanged()
    }
}