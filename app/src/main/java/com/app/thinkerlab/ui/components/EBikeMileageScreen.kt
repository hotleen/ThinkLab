package com.app.thinkerlab.ui.components

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DotProperties
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.Line

@Preview(showBackground = true)
@Composable
fun EBikeHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .padding(start = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")

        Text(text = "里程记录")

        Box(modifier = Modifier.width(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun EBikeLineChart() {
    LineChart(
        data = remember {
            listOf(
                Line(
                    label = "Windows",
                    values = listOf(28.0, 41.0, 5.0, 10.0, 35.0),
                    color = SolidColor(Color(0xFF23af92)),
                    curvedEdges = true,
                    dotProperties = DotProperties(
                        enabled = true,
                        color = SolidColor(Color.White),
                        strokeWidth = 4.dp,
                        radius = 7.dp,
                        strokeColor = SolidColor(Color.Cyan),
                    )
                ),
            )
        },
        curvedEdges = false
    )
}

@Preview
@Composable
fun MonthSelector() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("2025年 11月", fontSize = 16.sp)
            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
        }

        Text("累计：245km", color = Color.Gray, fontSize = 14.sp)
    }
}

@Preview
@Composable
fun PreviewRideRecordCard() {
    RideRecordCard(RideRecord("11-20", "周四", 52.5, 15, "单人"))
}

@Composable
fun RideRecordCard(item: RideRecord) {

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // 日期 + 天气
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("${item.date}  ${item.weekday}", fontSize = 14.sp, color = Color.Gray)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("${item.temp}°C", fontSize = 14.sp)
                    Spacer(Modifier.width(6.dp))
                    Icon(Icons.Default.Star, contentDescription = null)
                }
            }

            Spacer(Modifier.height(8.dp))

            // KM 数字
            Text(
                text = "${item.km} km",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(6.dp))
            Text("[${item.type}]", color = Color.Gray, fontSize = 13.sp)

        }
    }
}


data class RideRecord(
    val date: String,
    val weekday: String,
    val km: Double,
    val temp: Int,
    val type: String,
    val tag: RideTag? = null
)

data class RideTag(
    val title: String,
    val bgColor: Color
)

val sampleData = listOf(
    RideRecord("11-20", "周四", 52.5, 15, "单人"),
    RideRecord(
        "11-15", "周五", 30.2, -2, "多人",
        tag = RideTag("打破新高", Color(0xFFF7B500))
    ),
    RideRecord(
        "11-15", "周五", 30.2, -2, "多人",
        tag = RideTag("成绩下滑", Color(0xFFE85C5C))
    ),
    RideRecord("11-10", "周一", 48.0, 10, "单人")
)


