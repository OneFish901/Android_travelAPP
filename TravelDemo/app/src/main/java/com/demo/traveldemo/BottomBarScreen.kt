package com.demo.traveldemo


sealed class BottomBarScreen(
    val title: String,
    val icon: Int,
    val description: String,
    val page: Int
) {
    object Home : BottomBarScreen(
        title = "首页",
        icon = R.drawable.round_home_24,
        description = "Home description",
        page = 0
    )

    object Profile : BottomBarScreen(
        title = "建议",
        icon = R.drawable.round_stacked_line_chart_24,
        description = "Profile description",
        page = 1
    )

    object Settings : BottomBarScreen(
        title = "评价",
        icon = R.drawable.round_note_alt_24,
        description = "Settings description",
        page = 2
    )

    object Person : BottomBarScreen(
        title = "个人",
        icon = R.drawable.round_person_24,
        description = "Person description",
        page = 3
    )
}
