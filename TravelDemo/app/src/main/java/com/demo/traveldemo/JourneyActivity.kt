package com.demo.traveldemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NavGraph()
        }
    }
}
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val journeyViewModel: JourneyViewModel = viewModel()

    NavHost(navController, startDestination = "main") {
        composable("main") {
           MainScreen(navController)
        }
        composable("home") { TravelHome(navController,journeyViewModel) }
        composable("detail/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")
                if (id != null) {
                    ScenicSpotDetail( navController, id)
                }
            }
        composable("recommend") { RecommendationList(navController,journeyViewModel) }
        composable("review") { UserReviews(journeyViewModel) }
        composable("person") { Person() }
    }
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile,
        BottomBarScreen.Settings,
        BottomBarScreen.Person,
    )
    val pagerState = PagerState(currentPage = 0)
    // Create the NavController for managing navigation
    val journeyViewModel: JourneyViewModel = viewModel()

    Scaffold(
        bottomBar = { BottomBar(pagerState = pagerState, screens = screens) }
    ) { innerPaddingValues ->
        Box(
            modifier = Modifier
                .padding(innerPaddingValues)
                .background(color = Color(0xfffff6ef))
        ) {
            BottomNavGraph(pagerState = pagerState,navController,journeyViewModel)
        }
    }

}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomBar(
    pagerState: PagerState,
    screens: List<BottomBarScreen>
) {
    Row(
        modifier = Modifier
            .height(60.dp)
            .background(Color(0xfffff6ef))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                pagerState = pagerState
            )
        }
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun AddItem(
    screen: BottomBarScreen,
    pagerState: PagerState
) {
    val coroutineScope = rememberCoroutineScope()
    val selected = pagerState.currentPage == screen.page
    val background = if (selected) Color(0xfff5c980) else Color(0xfffff6ef)

    Box(
        modifier = Modifier
            .height(40.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(screen.page)
                    }
                }
            )
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(screen.icon),
                contentDescription = screen.description,
                tint = Color.Black
            )
            Text(
                text = screen.title,
                color = Color.Black
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomNavGraph(
    pagerState: PagerState,navController: NavController,journeyViewModel: JourneyViewModel
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            4,
            state = pagerState
        ) { page ->
            when (page) {
                0 -> TravelHome(navController,journeyViewModel)
                1 -> RecommendationList(navController,journeyViewModel)
                2 -> UserReviews(journeyViewModel)
                3 -> Person()
            }
        }
    }
}
