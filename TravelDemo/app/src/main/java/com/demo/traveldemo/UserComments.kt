package com.demo.traveldemo

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun UserReviews(viewModel: JourneyViewModel) {
    // 获取所有评论
    val comments by viewModel.getAllComments().observeAsState(listOf())

    // 如果没有评论，显示提示文本
    if (comments.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "暂无评论，快来写个评论吧！",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }

    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(comments) {index, comment ->
                CommentItem(comment,index)
            }
        }
    }
}

@Composable
fun CommentItem(review: Review,index:Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFF6EF))
            .padding(16.dp)) {
            // 显示评论内容
            Text(
                text = "用户${index+1}：${review.commentText}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}