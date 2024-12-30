package com.demo.traveldemo

import android.graphics.BitmapFactory
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController



data class Attraction(val id: String, val name: String,val description:String, val imageRes: Int)

//首页显示
val sampleAttractions = listOf(
    Attraction(
        id = "1",
        name = "丽江古城 (Lijiang Old Town)",
        description = "丽江古城位于云南省，是一个拥有800多年历史的纳西族古城。古城以其完好保存的古建筑群、独特的民族文化和水系布局而闻名，是国内外游客喜爱的旅游胜地。",
        imageRes = R.drawable.lijiang // 丽江古城的图片资源ID
    ),
    Attraction(
        id = "2",
        name = "西湖 (West Lake)",
        description = "西湖位于浙江省杭州市，是中国最著名的湖泊之一。西湖以其秀丽的自然风光和丰富的历史文化遗迹而著称，是文人墨客吟诗作画的好地方，也是游客们休闲游览的热门景点。",
        imageRes = R.drawable.westlake // 西湖的图片资源ID
    ),
    Attraction(
        id = "3",
        name = "长城 (Great Wall)",
        description = "长城是中国古代的一项伟大工程，横贯中国北部，全长超过万里。长城以其雄伟壮观的建筑和重要的历史意义而闻名，是中外游客必游的景点之一。",
        imageRes = R.drawable.greatwall // 长城的图片资源ID
    ),
    Attraction(
        id = "4",
        name = "桂林山水 (Guilin Landscapes)",
        description = "桂林山水位于广西壮族自治区，以其独特的喀斯特地貌和秀美的自然风光而闻名。这里的山峰耸立、江水如镜，被誉为“桂林山水甲天下”，是摄影爱好者和游客们的天堂。",
        imageRes = R.drawable.guilin // 桂林山水的图片资源ID
    ),
    Attraction(
        id = "5",
        name = "兵马俑 (Terracotta Army)",
        description = "兵马俑位于陕西省西安市，是秦始皇陵的一部分，也是世界上最大的地下军事博物馆。兵马俑以其精湛的雕塑技艺和庞大的规模而闻名，吸引了无数国内外游客前来参观。",
        imageRes = R.drawable.terracotta //兵马俑的图片资源ID
    ),
    Attraction(
        id = "6",
        name = "张家界天门山 (Tianmen Mountain in Zhangjiajie)",
        description = "天门山位于湖南省张家界市，以其险峻的山势、壮丽的自然风光和神秘的传说而闻名。天门山的玻璃栈道、天门洞等景点更是吸引了大量游客前来探险和挑战。",
        imageRes = R.drawable.tianmen //张家界天门山的图片资源ID
    ),
    Attraction(
        id = "7",
        name = "黄山 (Mount Huang)",
        description = "黄山位于安徽省，是中国著名的风景名胜区之一。黄山以其奇松、怪石、云海、温泉和冬雪五绝而闻名，被誉为“天下第一奇山”，是国内外游客喜爱的旅游胜地。",
        imageRes = R.drawable.huangshan //黄山的图片资源ID
    ),
    Attraction(
        id = "8",
        name = "九寨沟 (Jiuzhaigou Valley)",
        description = "九寨沟位于四川省，以其独特的原始森林、碧绿的湖泊和丰富的野生动植物资源而闻名。九寨沟的四季景色各异，尤以秋季的五彩斑斓最为迷人，是摄影爱好者和自然爱好者的天堂。",
        imageRes = R.drawable.jiuzhaigou_new //九寨沟的图片资源ID
    )
)


//首页布局
@Composable
fun TravelHome(navController: NavController, viewModel: JourneyViewModel) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFFFFF6EF))
    ) {
        items(sampleAttractions) { attraction ->
            AttractionItem(attraction, navController, viewModel)
        }
    }
}
@Composable
fun AttractionItem(attraction: Attraction, navController: NavController, viewModel: JourneyViewModel) {
    val context = LocalContext.current
    val bitmap = loadBitmapFromResource(context, attraction.imageRes)

    // 使用Row来实现横向排列
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                viewModel.incrementClick(attraction.id)  // 每次点击时更新点击次数
                navController.navigate("detail/${attraction.id}")
            },

        horizontalArrangement = Arrangement.spacedBy(8.dp), // 图片和文字之间的间隔
        verticalAlignment = Alignment.CenterVertically // 垂直方向居中对齐
    ) {
        // 图片部分
        Box(
            modifier = Modifier
                .size(120.dp)  // 设置图片的固定宽度和高度
                .clip(RoundedCornerShape(8.dp)) // 圆角效果
                .background(Color.Gray) // 默认背景颜色，如果图片加载失败
        ) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = attraction.name,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // 内容部分
        Column(
            modifier = Modifier
                .weight(1f)  // 文字部分占满剩余空间
                .padding(8.dp),

        ) {
            Text(
                text = attraction.name,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(bottom = 4.dp)
            )
            Text(
                text = "点击查看详情",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
            )
        }
    }
}

//首页图片显示设置
fun loadBitmapFromResource(context: android.content.Context, resourceId: Int): android.graphics.Bitmap {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(context.resources, resourceId, options)

    // 根据图片大小调整采样大小
    var scale = 1
    while (options.outWidth / scale > 1024 || options.outHeight / scale > 1024) {
        scale *= 2
    }

    options.inJustDecodeBounds = false
    options.inSampleSize = scale

    return BitmapFactory.decodeResource(context.resources, resourceId, options)
}
