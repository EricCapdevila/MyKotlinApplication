package com.example.mykotlinapplication.utils

import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity

object LayoutUtils {
    private fun getDisplayMetrics (activity : AppCompatActivity) : DisplayMetrics {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.getDefaultDisplay().getMetrics(displayMetrics)
        return displayMetrics
    }
    fun getScreenWidth(activity : AppCompatActivity) : Int {
        return getDisplayMetrics(activity).widthPixels
    }
    private fun getScreenHeight(activity : AppCompatActivity) : Int {
        return getDisplayMetrics(activity).heightPixels
    }
    fun oneLineInputHeight(activity : AppCompatActivity) : Int {return getScreenHeight(activity)/8}
    fun multipleLineInputHeight(activity : AppCompatActivity) : Int {return getScreenHeight(activity)/3}
}