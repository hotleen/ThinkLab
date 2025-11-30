package com.app.thinkerlab.ui.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.app.thinkerlab.R

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val selectedIconResId: Int? = null,
    val unselectedIconResId: Int? = null
) {
    object Home: Screen(
        "home",
        "首页",
        Icons.Default.Home,
        R.drawable.icon_chat_m,
        R.drawable.icon_chat_n
    )
    object Explore: Screen(
        "explore",
        "探索",
        Icons.Default.Search,
        R.drawable.icon_discovery_n,
        R.drawable.icon_discovery
    )
    object Profile: Screen(
        "profile",
        "我的",
        Icons.Default.Person,
        R.drawable.icon_me_m,
        R.drawable.icon_me_n
    )
}