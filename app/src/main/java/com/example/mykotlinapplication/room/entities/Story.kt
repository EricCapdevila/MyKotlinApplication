package com.example.mykotlinapplication.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Story (
        @ColumnInfo(name = "title") val title: String?,
        @ColumnInfo(name = "abstract") val abstract: String?
){
        @PrimaryKey(autoGenerate = true) val id: Int = 0
}