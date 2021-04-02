package com.example.mykotlinapplication.room.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Story")
data class Story (
        @PrimaryKey @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "resume") val resume: String?
)