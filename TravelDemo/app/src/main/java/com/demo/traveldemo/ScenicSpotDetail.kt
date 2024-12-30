package com.demo.traveldemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ScenicSpotDetail(navController: NavController, attractionId: String) {
    val context = LocalContext.current
    val attraction = sampleAttractions.find { it.id == attractionId }
    val db = TravelDatabase.getDatabase(context)
    val commentDao = db.commentDao()
    val comments = commentDao.fetchCommentsForSpot(attractionId).observeAsState(emptyList())

    attraction?.let {
        val bitmap = loadBitmapFromResource(context, it.imageRes)
        var userInput by remember { mutableStateOf("") }

        Scaffold(
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState()) // 开启全局上下滑动
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp)
                    ) {
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = it.name,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.headlineMedium.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp)
                        )

                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "返回",
                                tint = Color.White
                            )
                        }
                    }

                    // 景点详情
                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "景点详情",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Text(
                            text = it.description,
                            style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray)
                        )
                    }

                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                        // 评论输入框部分（使用 OutlinedTextField）
                        OutlinedTextField(
                            value = userInput,
                            onValueChange = { userInput = it },
                            label = { Text("输入评论") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color(0xFFF5C980),  // 聚焦时的边框颜色
                                unfocusedBorderColor =  Color.LightGray,  // 未聚焦时的边框颜色
                                cursorColor = Color(0xFFF5C980),  // 光标颜色
                                focusedLabelColor = Color(0xFFF5C980)
                            ),
                            shape = MaterialTheme.shapes.small,  // 更圆的形状
                            maxLines = 5, // 设置最大行数
                            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
                            placeholder = { Text("输入评论内容...") }  // 提示文本
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // 提交评论按钮
                        Button(
                            onClick = {
                                if (userInput.isNotBlank()) {
                                    CoroutineScope(Dispatchers.IO).launch {
                                        commentDao.addNewComment(Review(spotId = attractionId, commentText = userInput))
                                        userInput = ""  // 提交后清空输入框
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),  // 提升按钮的高度
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFFF6EF), // 深绿色背景色
                                contentColor = Color.Black // 按钮文字颜色
                            ),
                            shape = MaterialTheme.shapes.medium,  // 圆角按钮
                        ) {
                            Text(
                                text = "提交评论",
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                    }


                    Spacer(modifier = Modifier.height(24.dp))

                    // 评论列表部分
                    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                        if (comments.value.isNotEmpty()) {
                            comments.value.forEach { comment ->
                                CommentCard(comment = comment)
                            }
                        } else {
                            Text(
                                text = "暂无评论",
                                style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFFFFF6EF)),
                                modifier = Modifier.padding(top = 12.dp)
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun CommentCard(comment: Review) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        Column(modifier = Modifier
            .background(Color(0xFFFFF6EF)).fillMaxWidth()
            .padding(16.dp)
        ) {
            Text(
                text = "用户评论",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = comment.commentText,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black)
            )
        }
    }
}
