package com.demo.traveldemo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ReviewDao {
    @Query("SELECT * FROM Review WHERE spotId = :attractionId")
    fun fetchCommentsForSpot(attractionId: String): LiveData<List<Review>>

    @Query("SELECT * FROM Review")
    fun retrieveAllComments(): LiveData<List<Review>>

    @Insert
    suspend fun addNewComment(review: Review)
}
