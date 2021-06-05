package com.example.mykotlinapplication.section_landing.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mykotlinapplication.R
import com.example.mykotlinapplication.databinding.NewStoryFragmentBinding
import com.example.mykotlinapplication.section_landing.activity.NewStoryActivity
import com.example.mykotlinapplication.section_landing.viewmodel.NewStoryViewModel
import com.example.mykotlinapplication.utils.LayoutUtils

class NewStoryFragment : Fragment(){

    lateinit var binding : NewStoryFragmentBinding
    lateinit var headerString : String
    lateinit var buttonString : String

    lateinit var viewModel : NewStoryViewModel

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
        viewModel = activity?.let { ViewModelProvider(it).get(NewStoryViewModel::class.java) } ?:
            throw Exception("Invalid Activity")
        getExtras()
        setStyles()
        setViews()
        setButtonListener()
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

    private fun setButtonListener(){
        val nextStep = if (tag == TITLE) this::goToAbstractFragment
        else  this::finishProcess
        binding.button.setOnClickListener{
            setStoryValues()
            nextStep()
        }
    }

    private fun goToAbstractFragment() {
        (activity as NewStoryActivity).loadFragment(
            getString(R.string.new_story_abstract),
            getString(R.string.new_story_finish)
        )
    }

    private fun finishProcess(){
        viewModel.setStory()
        (activity as NewStoryActivity).finish()
    }

    private fun setStoryValues(){
        if(tag.equals(TITLE)) viewModel.title =  binding.textInput.text.toString()
        else viewModel.abstract =  binding.textInput.text.toString()
    }
}