package com.example.mykotlinapplication.landing.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mykotlinapplication.R
import com.example.mykotlinapplication.databinding.ActivityLandingBinding
import com.example.mykotlinapplication.landing.fragment.NewStoryFragment

class LandingActivity: AppCompatActivity()  {

    private lateinit var binding : ActivityLandingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_landing)
        setListener()
    }

    fun setListener(){
        binding.buttonNew.setOnClickListener {
            startActivity(Intent(this, NewStoryActivity::class.java))
        }
    }
}