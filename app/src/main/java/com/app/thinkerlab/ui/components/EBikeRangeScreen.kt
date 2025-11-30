package com.app.thinkerlab.ui.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.thinkerlab.R

/**
 * 电动车里程续航总览页面
 */

@Composable
fun EBikePage() {
    Column(Modifier.background(Color(0xFFF1F5F2)).statusBarsPadding()) {
        ScooterCard {
            Log.i("testHzy", "setting clicked!")
        }
        RightChartCard()
        MileageCard()
        FooterCard()
    }
}

@Composable
fun EBikeDescription() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Send,
            contentDescription = "EBike",
            modifier = Modifier.size(20.dp)
        )

        Text(
            text = "我的小电驴（爱玛）", modifier = Modifier.weight(1f), textAlign = TextAlign.Center
        )

        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "settings",
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun FooterCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFDFF8E6) // 浅绿色背景
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // 绿色卡片是平的
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // 左侧图标 + 文案
            Row(verticalAlignment = Alignment.CenterVertically) {

                // 💰 金钱图标 💰
                Image(
                    painter = painterResource(id = R.drawable.money_pag),
                    contentDescription = null,
                    modifier = Modifier.size(26.dp)
                )



                Spacer(Modifier.width(12.dp))

                Column {
                    Text(
                        text = "省钱秘籍",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF222222)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "骑行 1200km，比中枢省木 ¥650",
                        fontSize = 14.sp,
                        color = Color(0xFF2C7A3F)
                    )
                }
            }

            // 右侧 + 按钮（蓝色圆形）
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(Color(0xFF3B8BFF), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(26.dp)
                )
            }
        }
    }
}

@Composable
fun ScooterCard(
    title: String = "我的小电驴（爱玛 卫士）", onSettingsClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F7FA)   // ✅ 根据设计图更新背景色
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // ✅ 设计里没有阴影
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.Send, // 替换成你的电动车图标
                contentDescription = null, modifier = Modifier.size(24.dp)
            )

            Text(
                text = title,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold   // ✅ 根据设计加粗
                ),
                color = Color(0xFF1A1A1A)
            )

            IconButton(
                onClick = onSettingsClick, modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Settings, contentDescription = "settings"
                )
            }
        }
    }
}

@Composable
fun RightChartCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {

            // 标题
            Text(
                text = "续航调整",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF222222)
            )

            Spacer(Modifier.height(16.dp))

            // 图表区
            Chart()

            Spacer(Modifier.height(12.dp))

            // 提示
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painterResource(id = R.drawable.ic_lightbulb),
                    contentDescription = null,
                    tint = Color(0xFFFFC433),
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    "提示：最近气温下降，续航成绩略低",
                    fontSize = 13.sp,
                    color = Color(0xFF666666)
                )
            }
        }
    }
}

@Composable
fun Chart() {
    val values = listOf(30f, 42f, 50f, 63f) // 四个点
    val maxValue = 70f

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {

        // 左侧竖向文字
        Column(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Text("实\n际\n续\n航", fontSize = 12.sp, color = Color.Gray)
        }

        Canvas(
            modifier = Modifier
                .padding(start = 30.dp) // 给左侧刻度文字留空间
                .fillMaxSize()
        ) {
            val chartWidth = size.width
            val chartHeight = size.height

            val spacing = chartWidth / (values.size - 1)
            val points = values.mapIndexed { index, value ->
                Offset(
                    x = spacing * index,
                    y = chartHeight - (value / maxValue * chartHeight)
                )
            }

            // 区域阴影
            val areaPath = Path().apply {
                moveTo(points.first().x, chartHeight)
                points.forEach { p -> lineTo(p.x, p.y) }
                lineTo(points.last().x, chartHeight)
                close()
            }
            drawPath(
                path = areaPath,
                color = Color(0x334D88FF) // 蓝色透明填充
            )

            // 折线
            val linePath = Path().apply {
                moveTo(points.first().x, points.first().y)
                points.drop(1).forEach { p -> lineTo(p.x, p.y) }
            }
            drawPath(
                path = linePath,
                color = Color(0xFF4D88FF),
                style = Stroke(width = 4f)
            )

            // 圆点
            points.forEach {
                drawCircle(
                    color = Color.White,
                    radius = 8.dp.toPx()
                )
                drawCircle(
                    color = Color(0xFF4D88FF),
                    radius = 6.dp.toPx()
                )
            }
        }
    }
}


@Composable
fun MileTrendCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F9FB)),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "趋势图", fontSize = 15.sp, color = Color(0xFF222222))
            }
            Spacer(Modifier.height(10.dp))
            Row {
                Text(
                    text = "提醒：最近气温下降，续航受低温影响",
                    fontSize = 10.sp,
                    color = Color(0xFF222222)
                )
            }
        }
    }
}

@Composable
fun MileageCard() {
    //card背景色区域不包括
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF8F9FB)
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {

            // ⭐ 两行标题
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Color.Unspecified  // ⭐ 保留 SVG 原色（#FFC433）
                    )
                    Spacer(Modifier.width(6.dp))
                    Text("里程数", fontSize = 15.sp, color = Color(0xFF222222))
                }

                Spacer(Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(Modifier.width(6.dp))
                    Text("平均满电续航（km）", fontSize = 15.sp, color = Color(0xFF222222))
                }
            }

            Spacer(Modifier.height(14.dp))

            // ⭐ 大数字
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    "52.5",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A1A)
                )
                Spacer(Modifier.width(4.dp))
                Text("km", fontSize = 16.sp, color = Color.Gray)
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = "打败了 85% 的同款车友",
                fontSize = 14.sp,
                color = Color(0xFF4D88FF)
            )

            Spacer(Modifier.height(16.dp))

            Divider(thickness = 1.dp, color = Color(0xFFE5E6E8))

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("最近：48km", fontSize = 13.sp, color = Color.Gray)
                Text("历史最佳：60km", fontSize = 13.sp, color = Color.Gray)
            }
        }
    }
}


@Composable
fun RecentRangeCard() {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(250.dp)
            .padding(20.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Red)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 12.dp)
        ) {
            Row {
                Icon(imageVector = Icons.Default.Star, contentDescription = "star")
                Text("核心数据")
            }
            Row {
                Icon(imageVector = Icons.Default.Star, contentDescription = "star")
                Text("平均满电续航")
            }
            Row {
                Text("52.5")
                Text("km")
            }
            Row {
                Text("打败了85%的车友")
            }

            Divider(color = Color.Gray, thickness = 1.dp)

            Row {
                Text("最近行驶：59km 历史最佳：80km")
            }
        }
    }
}

@Composable
@Preview
fun PreviewPage() {
    EBikePage()
}

@Composable
@Preview
fun PreviewFooter() {
    FooterCard()
}

@Composable
@Preview
fun PreviewRightCard() {
    RightChartCard()
}

@Composable
@Preview
fun PreviewMileTrend() {
    MileTrendCard()
}

@Composable
@Preview
fun PreviewMiles() {
    MileageCard()
}

@Composable
@Preview(showBackground = true)
fun PreviewRecentCard() {
    RecentRangeCard()
}

@Composable
@Preview(showBackground = true)
fun PreviewScooter() {
    ScooterCard() {
        Log.i("testHzy", "setting click!")
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewEBike() {
    EBikeDescription()
}