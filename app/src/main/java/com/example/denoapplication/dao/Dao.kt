package com.example.denoapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.denoapplication.model.PostResponseItem

@Dao
interface PostDao {

    @Insert
    suspend fun insertPosts(posts: List<PostResponseItem>)

    @Query("SELECT * FROM post")
    suspend fun getAllPosts(): List<PostResponseItem>
}
