package com.example.mykotlinapplication.landing.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinapplication.R
import com.example.mykotlinapplication.landing.activity.MainStoryActivity
import com.example.mykotlinapplication.landing.activity.MainStoryActivity.Companion.SELECTED_STORY
import com.example.mykotlinapplication.landing.activity.NewStoryActivity
import com.example.mykotlinapplication.room.entities.Story
import com.google.android.material.button.MaterialButton

class LandingListAdapter(var stories: List<Story>,
                         var context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_BUTTON = 0
        const val VIEW_TYPE_STORY = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       // val binding : LandingListItemOldStoryBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.landing_list_item_old_story, parent, false)
        return if(viewType == VIEW_TYPE_BUTTON)  NewStoryViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.landing_list_item_new_story, parent, false), context)
        else  StoryViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.landing_list_item_old_story, parent, false), context)
    }

    override fun getItemCount(): Int {
        return stories.size + 1
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0){
            (holder as NewStoryViewHolder).setListeners()
        } else {
            (holder as StoryViewHolder).apply{
                setListeners(stories[position - 1])
                setData(stories[position - 1])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
       return if (position == 0) VIEW_TYPE_BUTTON
        else VIEW_TYPE_STORY
    }
}


class NewStoryViewHolder(itemView: View, var context : Context) : RecyclerView.ViewHolder(itemView) {
    fun setListeners(){
        itemView.findViewById<MaterialButton>(R.id.button_new).setOnClickListener{
            context.startActivity(Intent(context, NewStoryActivity::class.java))
        }
    }
}

class StoryViewHolder(itemView: View, var context : Context) : RecyclerView.ViewHolder(itemView){
   // var binding : LandingListItemOldStoryBinding = DataBindingUtil.inflate(LayoutInflater.from(context))
    //var binding : LandingListItemOldStoryBinding = DataBindingUtil.setContentView(context as LandingActivity, R.layout.landing_list_item_old_story)
    fun setData(story : Story){
        itemView.findViewById<MaterialButton>(R.id.button_story).text = story.title
    }
    fun setListeners(story : Story){
        itemView.findViewById<MaterialButton>(R.id.button_story).setOnClickListener {
            context.startActivity(Intent(context, MainStoryActivity::class.java).apply{
                putExtra(SELECTED_STORY, story.title)
            })
        }
    }
}