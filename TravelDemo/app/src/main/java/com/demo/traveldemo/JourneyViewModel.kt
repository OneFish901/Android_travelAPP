package com.demo.traveldemo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class JourneyViewModel : ViewModel() {
    // 使用一个Map来保存景点的点击次数
    private val _attractionClicks = mutableMapOf<String, Int>()
    val attractionClicks = mutableStateOf<Map<String, Int>>(_attractionClicks)

    // 更新景点的点击次数
    fun incrementClick(attractionId: String) {
        val currentCount = _attractionClicks[attractionId] ?: 0
        _attractionClicks[attractionId] = currentCount + 1
        attractionClicks.value = _attractionClicks
    }

    // 获取点击次数
    fun getClickCount(attractionId: String): Int {
        return _attractionClicks[attractionId] ?: 0
    }

    private val mReviewDao: ReviewDao = TravelDatabase.getDatabase(JourneyApplication.instance.getAppContext()).commentDao()

    // 获取所有评论
    fun getAllComments(): LiveData<List<Review>> {
        return mReviewDao.retrieveAllComments()
    }
}
