package com.example.mykotlinapplication.landing.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mykotlinapplication.R
import com.example.mykotlinapplication.databinding.ActivityMainStoryBinding
import com.example.mykotlinapplication.landing.viewmodel.StoryViewModel
import com.example.mykotlinapplication.landing.viewmodel.ViewModelFactory
import com.example.mykotlinapplication.repository.Repository
import com.example.mykotlinapplication.room.entities.Story
import com.google.android.material.tabs.TabLayout

class MainStoryActivity : AppCompatActivity(){

    companion object {
        const val SELECTED_STORY = "Story"
    }
    private lateinit var binding : ActivityMainStoryBinding
    private lateinit var viewModel : StoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_story)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_story )
        viewModel = ViewModelProvider(this, ViewModelFactory(Repository(this)))
            .get(StoryViewModel::class.java)
        setTabIcons()
        setListeners()
    }

    private fun setTabIcons(){
        val icons = listOf(R.drawable.ic_home, R.drawable.ic_characters, R.drawable.ic_places,
            R.drawable.ic_timeline, R.drawable.ic_delete)
        for (icon in icons){ binding.mainStoryTabs.apply{addTab(newTab().setIcon(icon))} }
    }

    private fun setListeners(){
        binding.mainStoryTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let{
                    when(it.position){
                        0 -> {}
                        1 -> {}
                        2 -> {}
                        3 -> {}
                        4 -> {deleteStory()}
                    }
                }
            }
        })
    }


    private fun deleteStory(){
         intent.extras?.let {
             it.getString(SELECTED_STORY)?.let { title ->
                 viewModel.deleteStoryWithTitle(title)
             }
         }
        finish()
    }

}