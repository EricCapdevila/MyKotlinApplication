package com.example.mykotlinapplication.section_storymain.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mykotlinapplication.R
import com.example.mykotlinapplication.databinding.NewStoryFragmentBinding
import com.example.mykotlinapplication.databinding.StoryMainFragmentBinding
import com.example.mykotlinapplication.section_landing.activity.NewStoryActivity
import com.example.mykotlinapplication.section_landing.viewmodel.NewStoryViewModel
import com.example.mykotlinapplication.section_landing.viewmodel.StoryViewModel
import com.example.mykotlinapplication.section_storymain.activity.MainStoryActivity
import com.example.mykotlinapplication.section_storymain.activity.MainStoryActivity.Companion.SUMMARY
import com.example.mykotlinapplication.section_storymain.activity.MainStoryActivity.Companion.TITLE
import com.example.mykotlinapplication.utils.LayoutUtils

class StoryMainFragment : Fragment(){

    lateinit var binding : StoryMainFragmentBinding
    private var title = ""
    private var summary = ""

    companion object {

        fun getInstance(title : String,  summary: String) : StoryMainFragment{
          return StoryMainFragment().apply{
                        arguments = Bundle().apply{
                            putString(TITLE, title)
                            putString(SUMMARY, summary)
                        }
          }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.story_main_fragment, container, false)
        getExtras()
        setStyles()
        setViews()
        return binding.root
     }

    private fun getExtras(){
        arguments?.let{
           title = it.getString(TITLE, "")
           summary = it.getString(SUMMARY, "")
        }
    }

    private fun setStyles(){
        val adaptedHeight = if(tag == TITLE) {
            LayoutUtils.oneLineInputHeight(activity as MainStoryActivity)
        } else {
            LayoutUtils.multipleLineInputHeight(activity as MainStoryActivity)
        }
        binding.storySummary.height = adaptedHeight
    }

    private fun setViews(){
        binding.apply {
            storyTitle.setText(title)
            storySummary.setText(summary)
        }
    }

}