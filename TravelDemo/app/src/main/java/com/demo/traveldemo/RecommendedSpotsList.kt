package com.demo.traveldemo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RecommendationList(navController : NavController, viewModel: JourneyViewModel) {
    // 获取按点击次数排序后的景点列表
    val sortedAttractions = sampleAttractions
        .filter { viewModel.getClickCount(it.id) > 0 }  // 只保留被点击过的景点
        .sortedByDescending { viewModel.getClickCount(it.id) }  // 按点击次数降序排序


    // 如果没有景点被点击过，显示提示文本
    if (sortedAttractions.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "没有景点被点击过，快去浏览景点吧！",
                style = MaterialTheme.typography.bodyMedium
            )
        }

    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(sortedAttractions) { attraction ->
                AttractionItem(attraction, navController, viewModel)
            }
        }
    }
}