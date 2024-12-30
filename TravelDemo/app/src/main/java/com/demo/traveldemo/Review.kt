package com.demo.traveldemo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Review(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val spotId: String,
    val commentText: String
)
