package com.example.mykotlinapplication.landing.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinapplication.R
import com.example.mykotlinapplication.databinding.LandingListItemOldStoryBinding
import com.example.mykotlinapplication.landing.activity.LandingActivity
import com.example.mykotlinapplication.landing.activity.NewStoryActivity
import com.example.mykotlinapplication.room.entities.Story
import com.google.android.material.button.MaterialButton


class TestAdapter2(
    var list: List<String>,
    private val context: Context
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_BUTTON = 0
        private const val TYPE_LETTER = 1
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_BUTTON -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.landing_list_item_new_story, parent, false)
                ButtonViewHolder(view, context)
            }
            TYPE_LETTER -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.landing_list_item_old_story, parent, false)
                LetterViewHolder(view, context)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ButtonViewHolder -> holder.bind()
            is LetterViewHolder -> {
                holder.apply{
                    bind(list[position - 1])
                    setListener()
                }
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_BUTTON
            else -> TYPE_LETTER
        }
    }
}

class LetterViewHolder(itemView: View, var context : Context) : RecyclerView.ViewHolder(itemView) {

    //var binding : LandingListItemOldStoryBinding = DataBindingUtil.setContentView(context as LandingActivity, R.layout.landing_list_item_old_story)

    fun bind(item: String) {
        itemView.findViewById<MaterialButton>(R.id.button_story).text = item
    }

    fun setListener(){
       // binding.buttonStory.setOnClickListener { print("calling listener for old stories") }
    }
}
class ButtonViewHolder(itemView: View, var context : Context) : RecyclerView.ViewHolder(itemView) {

    fun bind() {
        itemView.findViewById<MaterialButton>(R.id.button_new).text = "BUTTON"
    }
}
