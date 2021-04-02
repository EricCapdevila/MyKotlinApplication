package com.example.mykotlinapplication

import android.app.Application
import com.example.mykotlinapplication.repository.Repository
import com.example.mykotlinapplication.room.AppDatabase

class MyKotlinApplication : Application() {

    val database by lazy { AppDatabase.getInstance(this) }
    val repository by lazy { Repository(this) }
}