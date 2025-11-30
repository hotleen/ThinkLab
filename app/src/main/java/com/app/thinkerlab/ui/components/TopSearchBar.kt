package com.app.thinkerlab.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/**
 * 顶部搜索栏组件
 * 包含定位icon、城市名称和搜索框
 *
 * @param cityName 当前城市名称
 * @param onLocationClick 定位图标点击回调
 * @param onSearchClick 搜索框点击回调
 */
@Composable
fun TopSearchBar(
    cityName: String = "北京",
    onLocationClick: () -> Unit = {},
    onSearchClick: () -> Unit = {}
) {
    // 搜索提示词列表
    val searchHints = listOf(
        "搜索你想要的商品",
        "查找附近的商家",
        "搜索优惠活动",
        "查找特色服务"
    )
    
    // 当前显示的提示词索引
    var hintIndex by remember { mutableStateOf(0) }
    
    // 轮播提示词效果
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000) // 每3秒切换一次提示词
            hintIndex = (hintIndex + 1) % searchHints.size
        }
    }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 定位区域
        Row(
            modifier = Modifier
                .clickable { onLocationClick() }
                .padding(end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "定位",
                tint = Color(0xFF1AAD19), // 微信绿
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = cityName,
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
        
        // 搜索框
        Box(
            modifier = Modifier
                .weight(1f)
                .height(36.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xFFF5F5F5))
                .clickable { onSearchClick() },
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "搜索",
                    tint = Color.Gray,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = searchHints[hintIndex],
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 8.dp),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}