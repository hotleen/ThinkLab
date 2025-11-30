package com.app.thinkerlab.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import com.app.thinkerlab.ui.components.TopSearchBar
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.thinkerlab.ui.data.Screen

@Composable
@Preview
fun  PreviewMainPage() {
    MainScreen()
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val items = listOf(Screen.Home, Screen.Explore, Screen.Profile)

    Scaffold(
        bottomBar = {
            Column {
                // 顶部细分割线
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .background(Color(0xFFE5E5E5))
                )

                NavigationBar(
                    containerColor = Color(0xFFF8F8F8), // 微信Tab栏背景色
                    tonalElevation = 0.dp // 移除阴影
                ) {
                    val currentBackStackEntry = navController.currentBackStackEntryAsState()
                    val currentDestination = currentBackStackEntry.value?.destination
                    items.forEach { screen ->
                        val selected = currentDestination?.route == screen.route
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                if (!selected) {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                }
                            },
                            icon = {
                                if (screen.selectedIconResId != null && screen.unselectedIconResId != null) {
                                    Icon(
                                        painter = painterResource(if (selected) screen.selectedIconResId else screen.unselectedIconResId),
                                        contentDescription = screen.title,
                                        tint = if (selected) Color(0xFF1AAD19) else Color(0xFF888888),
                                        modifier = Modifier.size(24.dp) // 固定图标大小
                                    )
                                } else {
                                    Icon(
                                        screen.icon,
                                        contentDescription = screen.title,
                                        tint = if (selected) Color(0xFF1AAD19) else Color(0xFF888888),
                                        modifier = Modifier.size(24.dp) // 固定图标大小
                                    )
                                }
                            },
                            label = {
                                Text(
                                    screen.title,
                                    color = if (selected) Color(0xFF1AAD19) else Color(0xFF888888),
                                    fontSize = 12.sp, // 微信字体大小
                                    fontWeight = if (selected) FontWeight.Medium else FontWeight.Normal // 选中时稍粗
                                )
                            }
                        )
                    }
                }

            }
        }) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomePage() }
            composable(Screen.Explore.route) { ExplorePage() }
            composable(Screen.Profile.route) { ProfilePage() }
        }

    }
}

@Composable
fun HomePage() {
    var currentCity by remember { mutableStateOf("北京") }

    Column(modifier = Modifier.fillMaxSize()) {
        // 顶部搜索栏
        TopSearchBar(
            cityName = currentCity,
            onLocationClick = {
                // 这里可以处理定位逻辑
                // 示例：currentCity = "上海"
            },
            onSearchClick = {
                // 这里可以处理搜索逻辑
            }
        )

        // 主要内容区域
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Text("HomePage")
        }
    }
}

@Composable
fun ExplorePage() {
    var currentCity by remember { mutableStateOf("北京") }

    Column(modifier = Modifier.fillMaxSize()) {
        // 顶部搜索栏
        TopSearchBar(
            cityName = currentCity,
            onLocationClick = {
                // 这里可以处理定位逻辑
            },
            onSearchClick = {
                // 这里可以处理搜索逻辑
            }
        )

        // 主要内容区域
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Text("ExplorePage")
        }
    }
}

@Composable
fun ProfilePage() {
    var currentCity by remember { mutableStateOf("北京") }

    Column(modifier = Modifier.fillMaxSize()) {
        // 顶部搜索栏
        TopSearchBar(
            cityName = currentCity,
            onLocationClick = {
                // 这里可以处理定位逻辑
            },
            onSearchClick = {
                // 这里可以处理搜索逻辑
            }
        )

        // 主要内容区域
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Text("ProfilePage")
        }
    }
}