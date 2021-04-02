package com.example.mykotlinapplication.landing.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.mykotlinapplication.R
import com.example.mykotlinapplication.databinding.NewStoryFragmentBinding
import com.example.mykotlinapplication.landing.activity.NewStoryActivity
import com.example.mykotlinapplication.utils.LayoutUtils

class NewStoryFragment : Fragment(){

    lateinit var binding : NewStoryFragmentBinding
    lateinit var headerString : String
    lateinit var buttonString : String

    private fun goToAbstractFragment() {
        (activity as NewStoryActivity).loadFragment(
            getString(R.string.new_story_abstract),
            getString(R.string.new_story_finish)
        )
    }

     private fun finishProcess(){
        (activity as NewStoryActivity).finish()
    }

    companion object {

        const val TITLE = "TITLE"
        private const val HEADER = "HEADER"
        private const val BUTTON = "BUTTON"

        fun getInstance(header : String, button : String) : NewStoryFragment{
          return NewStoryFragment().apply{
                        arguments = Bundle().apply{
                            putString(HEADER, header)
                            putString(BUTTON, button)
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
            inflater, R.layout.new_story_fragment, container, false)
        getExtras()
        setStyles()
        setViews()
        setListener()
        return binding.root
     }

    private fun getExtras(){
        arguments?.let{
           buttonString = it.getString(BUTTON, "")
           headerString = it.getString(HEADER, "")
        }
    }
    private fun setStyles(){
        val adaptedHeight = if(tag == TITLE) {
            LayoutUtils.oneLineInputHeight(activity as NewStoryActivity)
        } else {
            LayoutUtils.multipleLineInputHeight(activity as NewStoryActivity)
        }
        binding.textInput.height = adaptedHeight
    }
    private fun setViews(){
        binding.apply {
            button.text = buttonString
            header.text = headerString
        }
    }
    private fun setListener(){
        val lambda = if (tag == TITLE) this::goToAbstractFragment
        else  this::finishProcess
        binding.button.setOnClickListener{lambda()}
    }
}