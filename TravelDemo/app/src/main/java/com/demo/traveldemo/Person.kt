package com.demo.traveldemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontFamily

@Composable
fun Person() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xfffff6ef) ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // 头像区域
            ProfileImage()

            // 姓名区域
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "XMUT",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                ),
                color = Color.Black
            )

            // 简介区域
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "在校学生 | 喜欢旅游",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif
                ),
                color = Color.Gray
            )

            // 联系方式区域
            Spacer(modifier = Modifier.height(16.dp))
            ContactInfo()

            // 更多信息按钮
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { /* TODO: Handle click */ }, colors = ButtonDefaults.buttonColors(

                backgroundColor = Color(0xfff0cb82) // 设置文本颜色为白色
            )) {
                Text(text = "更多信息", style = TextStyle(fontSize = 16.sp))
            }
        }
    }
}

@Composable
fun ProfileImage() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(120.dp)
            .background(Color.Gray, shape = CircleShape)
    ) {
        Image(
            painter = painterResource(id = R.drawable.snipaste_2024_12_01_23_20_23),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape), // 添加这个来裁剪图片为圆形
            contentScale = ContentScale.Crop // 裁剪图片以适应圆形
        )
    }
}

@Composable
fun ContactInfo() {
    Card(
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "电话：123-456-7890", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "邮箱：zhangsan@example.com", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "地址：北京市海淀区XX路", style = MaterialTheme.typography.body1)
        }
    }
}
