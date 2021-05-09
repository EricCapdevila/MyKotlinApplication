package com.example.mykotlinapplication.room.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StoryDao {

    //suspend can only be used in coroutines (inside GlobalScope.launch{},
    //room must be used in a coroutine
    // it tells that thred to hold until the suspend function is done,
    // then it can move to the next function)

    @Query("SELECT * FROM story")
    suspend fun getAll(): List<Story>

/*    @Query("SELECT * FROM story WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<Story>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Story*/

    @Insert
    suspend fun insertAll(vararg stories: Story)

    @Query("DELETE FROM story WHERE title = :title")
    suspend fun deleteByTitle(title: String)

    @Delete
    suspend fun delete(story: Story)
}