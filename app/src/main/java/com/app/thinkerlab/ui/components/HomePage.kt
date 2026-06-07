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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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

data class FinancialService(
    val title: String,
    val subtitle: String,
    val color: Color,
    val char: String
)

@Composable
fun HomePage(modifier: Modifier = Modifier, onCloudDriveClick: () -> Unit = {}) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFEDEDED)),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { TopNavBar() }
        item { BannerCard() }
        item { FeatureGridSection(onCloudDriveClick) }
        item { RecentUsageSection() }
        item { FinancialServicesSection() }
        item { Spacer(modifier = Modifier.height(8.dp)) }
    }
}

@Composable
private fun TopNavBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color(0xFF1A6DFF)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "M",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .height(36.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(Color(0xFFF2F2F2))
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "搜索",
                    tint = Color(0xFFB0B0B0),
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "搜索服务、账单或权益",
                    fontSize = 14.sp,
                    color = Color(0xFFB0B0B0)
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "通知",
            tint = Color(0xFF353535),
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
private fun BannerCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { /* TODO */ },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF1A6DFF))
                .padding(20.dp)
        ) {
            Column {
                Text(
                    "年度理财报告已上线",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "点击查看您的年度财富回顾",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }

            Row(
                modifier = Modifier.align(Alignment.BottomEnd),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(24.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color.White)
                )
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.5f))
                )
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.5f))
                )
            }
        }
    }
}

@Composable
private fun FeatureGridSection(onCloudDriveClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FeatureGridItem("云盘", Color(0xFF4A90D9), "云", onClick = onCloudDriveClick)
                FeatureGridItem("付款", Color(0xFF1AAD19), "付")
                FeatureGridItem("钱包", Color(0xFFF5A623), "钱")
                FeatureGridItem("转账", Color(0xFF7B68EE), "转")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FeatureGridItem("账单", Color(0xFFE74C3C), "账")
                FeatureGridItem("卡券", Color(0xFFFF6B6B), "卡")
                FeatureGridItem("权益", Color(0xFF2ECC71), "权")
                FeatureGridItem("客服", Color(0xFF3498DB), "客")
            }
        }
    }
}

@Composable
private fun FeatureGridItem(name: String, color: Color, char: String, onClick: () -> Unit = {}) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Text(
                char,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            name,
            fontSize = 12.sp,
            color = Color(0xFF353535)
        )
    }
}

@Composable
private fun RecentUsageSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "最近使用",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF353535)
                )
                Text(
                    "管理",
                    fontSize = 14.sp,
                    color = Color(0xFF1A6DFF),
                    modifier = Modifier.clickable { /* TODO */ }
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(Color(0xFFE5E5E5))
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                RecentUsageItem("电费缴纳", Color(0xFFF5A623), "电")
                RecentUsageItem("地铁乘车", Color(0xFF4A90D9), "地")
                RecentUsageItem("违章处理", Color(0xFFE74C3C), "违")
            }
        }
    }
}

@Composable
private fun RecentUsageItem(name: String, color: Color, char: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { /* TODO */ }
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Text(
                char,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            name,
            fontSize = 12.sp,
            color = Color(0xFF353535)
        )
    }
}

@Composable
private fun FinancialServicesSection() {
    val services = listOf(
        FinancialService("保险服务", "全面保障您的未来", Color(0xFF1A6DFF), "保"),
        FinancialService("贷款融资", "快捷审批，额度高", Color(0xFFF5A623), "贷"),
        FinancialService("财富投资", "专业理财，稳健增值", Color(0xFF2ECC71), "财")
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column {
            Text(
                "金融服务",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF353535),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(Color(0xFFE5E5E5))
            )

            services.forEachIndexed { index, service ->
                FinancialServiceItem(service)
                if (index < services.lastIndex) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.5.dp)
                            .padding(start = 72.dp)
                            .background(Color(0xFFE5E5E5))
                    )
                }
            }
        }
    }
}

@Composable
private fun FinancialServiceItem(service: FinancialService) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO */ }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(service.color),
            contentAlignment = Alignment.Center
        ) {
            Text(
                service.char,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                service.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF353535)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                service.subtitle,
                fontSize = 13.sp,
                color = Color(0xFF888888)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            ">",
            fontSize = 16.sp,
            color = Color(0xFFB0B0B0)
        )
    }
}

@Preview
@Composable
private fun PreviewHomePage() {
    HomePage()
}
