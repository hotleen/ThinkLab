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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CloudFolder(
    val name: String,
    val modifiedTime: String?,
    val isProtected: Boolean = false
)

@Composable
fun FilePage(modifier: Modifier = Modifier, onBack: () -> Unit = {}) {
    val folders = listOf(
        CloudFolder("隐藏空间", null, isProtected = true),
        CloudFolder("我的资源", "2026-05-21 21:14"),
        CloudFolder("慕课网", "2025-07-19 18:38"),
        CloudFolder("资料", "2025-01-04 09:08"),
        CloudFolder("应用软件", "2023-07-04 19:58"),
        CloudFolder("买的资料", "2023-07-03 22:03"),
        CloudFolder("影视", "2022-03-21 00:47"),
        CloudFolder("金融理财", "2020-09-05 09:33"),
    )

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEDEDED))
        ) {
            item { FileTopBar(onBack) }
            item { SearchSection() }
            item { FilterToolbar() }
            item {
                Text(
                    "全部文件",
                    fontSize = 14.sp,
                    color = Color(0xFF888888),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }
            items(folders) { folder ->
                FolderItem(folder)
            }
            item { Spacer(modifier = Modifier.height(80.dp)) }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .shadow(6.dp, CircleShape)
                .size(56.dp)
                .clip(CircleShape)
                .background(
                    Brush.linearGradient(
                        colors = listOf(Color(0xFF4A90D9), Color(0xFF1A6DFF))
                    )
                )
                .clickable { /* TODO: 新建文件夹/上传 */ },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "+",
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.Light
            )
        }
    }
}

@Composable
private fun FileTopBar(onBack: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 4.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "返回",
                tint = Color(0xFF353535)
            )
        }
        Text(
            "文件",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF353535),
            modifier = Modifier.weight(1f)
        )
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF2F2F2)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "⟳",
                    fontSize = 18.sp,
                    color = Color(0xFF555555)
                )
            }
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF2F2F2)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "⋯",
                    fontSize = 18.sp,
                    color = Color(0xFF555555)
                )
            }
        }
    }
}

@Composable
private fun SearchSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF2F2F2))
                .padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "搜索",
                tint = Color(0xFFB0B0B0),
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "搜索网盘文件",
                fontSize = 14.sp,
                color = Color(0xFFB0B0B0)
            )
        }
    }
}

@Composable
private fun FilterToolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilterChip("智能排序", hasDropdown = true)
            FilterChip("来源", hasDropdown = true)
            FilterChip("类型", hasDropdown = true)
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF2F2F2)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "⫧",
                    fontSize = 16.sp,
                    color = Color(0xFF555555)
                )
            }
        }
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFF2F2F2)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "▦",
                fontSize = 16.sp,
                color = Color(0xFF555555)
            )
        }
    }
}

@Composable
private fun FilterChip(text: String, hasDropdown: Boolean) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF2F2F2))
            .clickable { /* TODO */ }
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text,
                fontSize = 13.sp,
                color = Color(0xFF555555)
            )
            if (hasDropdown) {
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    "▾",
                    fontSize = 10.sp,
                    color = Color(0xFF888888)
                )
            }
        }
    }
}

@Composable
private fun FolderItem(folder: CloudFolder) {
    Column(modifier = Modifier.background(Color.White)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /* TODO: 打开文件夹 */ }
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (folder.isProtected) Color(0xFFFFF3E0)
                        else Color(0xFFFFF8E1)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    if (folder.isProtected) "🔒" else "📁",
                    fontSize = 22.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    folder.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF353535)
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (folder.modifiedTime != null) {
                    Text(
                        folder.modifiedTime,
                        fontSize = 12.sp,
                        color = Color(0xFFB0B0B0)
                    )
                } else {
                    Text(
                        "加密文件夹",
                        fontSize = 12.sp,
                        color = Color(0xFFF5A623)
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE5E5E5))
                    .clickable { /* TODO: 选择文件夹 */ },
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .padding(start = 72.dp)
                .background(Color(0xFFE5E5E5))
        )
    }
}

@Preview
@Composable
private fun PreviewFilePage() {
    FilePage()
}
