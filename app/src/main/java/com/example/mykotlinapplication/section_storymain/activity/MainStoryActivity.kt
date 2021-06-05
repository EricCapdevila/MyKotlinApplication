package com.example.mykotlinapplication.section_storymain.activity

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.mykotlinapplication.R
import com.example.mykotlinapplication.databinding.ActivityMainStoryBinding
import com.example.mykotlinapplication.section_landing.viewmodel.StoryViewModel
import com.example.mykotlinapplication.section_landing.viewmodel.ViewModelFactory
import com.example.mykotlinapplication.repository.Repository
import com.example.mykotlinapplication.section_storymain.fragment.StoryMainFragment
import com.google.android.material.tabs.TabLayout

class MainStoryActivity : AppCompatActivity(){

    companion object {
        const val TITLE = "TITLE"
        const val SUMMARY = "SUMMARY"
    }
    private lateinit var binding : ActivityMainStoryBinding
    lateinit var viewModel : StoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_story )
        viewModel = ViewModelProvider(this, ViewModelFactory(Repository(this)))
            .get(StoryViewModel::class.java)

        setTabIcons()
        setListeners()

        if(savedInstanceState == null){ //first time called, new activity
            callFirstFragment()
        }

    }

    private fun getExtra(key: String): String {
        intent.apply{
            getStringExtra(key)?.let{
                return it
            }
            return ""
        }
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
                        0 -> { goToHome() }
                        1 -> {}
                        2 -> {}
                        3 -> {}
                        4 -> { showDialog() }
                    }
                }
            }
        })
    }

    private fun goToHome(){

    }

    private fun callFirstFragment(){
        intent.apply{
            supportFragmentManager.commit{
                setReorderingAllowed(true)
                add(R.id.fragment_container_main, StoryMainFragment.getInstance(
                    getExtra(TITLE), getExtra(SUMMARY)))
            }
        }
    }

    private fun showDialog(){
      AlertDialog.Builder(this, R.style.MyAlertDialogStyle).apply{
          setMessage("Are you sure you want to delete?")
          setPositiveButton("Yes") { _, _ -> viewModel.deleteStoryWithTitle(getExtra(TITLE)) }
          setOnDismissListener { binding.mainStoryTabs.let{ it.selectTab(it.getTabAt(0)) } }
      }.show().getButton(DialogInterface.BUTTON_POSITIVE).setBackgroundColor(ContextCompat.getColor(this, R.color.main_blue))
    }


}