package com.example.mykotlinapplication.landing.activity

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.mykotlinapplication.R
import com.example.mykotlinapplication.databinding.ActivityMainStoryBinding
import com.example.mykotlinapplication.landing.fragment.NewStoryFragment
import com.example.mykotlinapplication.landing.viewmodel.NewStoryViewModel
import com.example.mykotlinapplication.landing.viewmodel.StoryViewModel
import com.example.mykotlinapplication.landing.viewmodel.ViewModelFactory
import com.example.mykotlinapplication.repository.Repository
import com.google.android.material.tabs.TabLayout

class MainStoryActivity : AppCompatActivity(){

    private lateinit var binding : ActivityMainStoryBinding
    private lateinit var viewModel : StoryViewModel
    private lateinit var tabs : TabLayout

override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_story)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_story )
        viewModel = ViewModelProvider(this, ViewModelFactory(Repository(this)))
            .get(StoryViewModel::class.java)
        setViews()
    }


    private fun setViews(){
        val icons = listOf(R.drawable.ic_home, R.drawable.ic_characters, R.drawable.ic_places,
            R.drawable.ic_timeline, R.drawable.ic_delete)
        for (icon in icons){ binding.mainStoryTabs.addTab(binding.mainStoryTabs.newTab().setIcon(icon))}


    }

    fun setTab(icon : Int) : TabLayout.Tab {
        return  TabLayout.Tab().apply{
            setIcon(icon)
        }
    }
}