package com.app.thinkerlab.ui.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home: Screen("home", "首页",Icons.Default.Home)
    object Explore: Screen("explore", "探索",Icons.Default.Search)
    object Profile: Screen("profile", "我的",Icons.Default.Person)
}