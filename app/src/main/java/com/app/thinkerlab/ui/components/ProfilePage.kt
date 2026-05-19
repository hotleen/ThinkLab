package com.app.thinkerlab.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfilePage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFEDEDED))
    ) {
        // 顶部标题栏
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEDEDED))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                "我的",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF353535)
            )
        }

        // 个人资料卡片
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .clickable { /* 跳转个人资料 */ }
                .padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // 头像占位
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF07C160)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "T",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        "ThinkLab",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF353535)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "微信号: ThinkLab_2024",
                        fontSize = 14.sp,
                        color = Color(0xFF888888)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 菜单列表
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(0.dp),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Column {
                ProfileMenuItem("个人信息", "个人资料设置")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .background(Color(0xFFE5E5E5))
                        .padding(start = 16.dp)
                )
                ProfileMenuItem("设置", "应用设置")
            }
        }
    }
}

@Composable
private fun ProfileMenuItem(title: String, subtitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* 跳转 */ }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                title,
                fontSize = 16.sp,
                color = Color(0xFF353535)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                subtitle,
                fontSize = 14.sp,
                color = Color(0xFFB0B0B0)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                ">",
                fontSize = 16.sp,
                color = Color(0xFFB0B0B0)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewProfilePage() {
    ProfilePage()
}
