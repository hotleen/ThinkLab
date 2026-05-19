package com.app.thinkerlab.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.thinkerlab.R

@Composable
fun MainBottomBar(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(Color(0xFFE5E5E5))
        )
        NavigationBar(
            containerColor = Color(0xFFF8F8F8),
            tonalElevation = 0.dp
        ) {
            NavigationBarItem(
                selected = selectedTab == 0,
                onClick = { onTabSelected(0) },
                icon = {
                    Icon(
                        painter = painterResource(if (selectedTab == 0) R.drawable.icon_chat_m else R.drawable.icon_chat_n),
                        contentDescription = "首页",
                        tint = if (selectedTab == 0) Color(0xFF1AAD19) else Color(0xFF888888),
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        "首页",
                        color = if (selectedTab == 0) Color(0xFF1AAD19) else Color(0xFF888888),
                        fontSize = 12.sp,
                        fontWeight = if (selectedTab == 0) FontWeight.Medium else FontWeight.Normal
                    )
                }
            )
            NavigationBarItem(
                selected = selectedTab == 1,
                onClick = { onTabSelected(1) },
                icon = {
                    Icon(
                        painter = painterResource(if (selectedTab == 1) R.drawable.icon_me_m else R.drawable.icon_me_n),
                        contentDescription = "我的",
                        tint = if (selectedTab == 1) Color(0xFF1AAD19) else Color(0xFF888888),
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        "我的",
                        color = if (selectedTab == 1) Color(0xFF1AAD19) else Color(0xFF888888),
                        fontSize = 12.sp,
                        fontWeight = if (selectedTab == 1) FontWeight.Medium else FontWeight.Normal
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewMainBottomBar() {
    MainBottomBar(selectedTab = 0, onTabSelected = {})
}
