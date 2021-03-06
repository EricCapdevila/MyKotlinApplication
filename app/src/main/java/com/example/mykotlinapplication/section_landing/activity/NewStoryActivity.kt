package com.example.mykotlinapplication.section_landing.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.mykotlinapplication.R
import com.example.mykotlinapplication.section_landing.fragment.NewStoryFragment
import com.example.mykotlinapplication.section_landing.fragment.NewStoryFragment.Companion.TITLE
import com.example.mykotlinapplication.section_landing.viewmodel.NewStoryViewModel
import com.example.mykotlinapplication.section_landing.viewmodel.ViewModelFactory
import com.example.mykotlinapplication.repository.Repository

class NewStoryActivity : AppCompatActivity(){

    lateinit var  viewModel: NewStoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_story_activity)
        viewModel = ViewModelProvider(this, ViewModelFactory(Repository(this)))
            .get(NewStoryViewModel::class.java)
        if(savedInstanceState == null){ //first time called, new activity
            supportFragmentManager.commit{
                setReorderingAllowed(true)
                add(R.id.fragment_container, NewStoryFragment.getInstance(
                    getString(R.string.new_story_title), getString(R.string.new_story_next)), TITLE)
            }
        }
    }

     fun loadFragment(header:String, button:String){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.apply{
            replace(R.id.fragment_container, NewStoryFragment.getInstance(header,button))
            disallowAddToBackStack()
            commit()
        }
    }
}