package com.app.thinkerlab.ui.components

import android.service.autofill.OnClickAction
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.thinkerlab.R
import com.app.thinkerlab.ui.graphic.SunIcon
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.DotProperties
import ir.ehsannarmani.compose_charts.models.Line

@Composable
fun EBikeMileageRouter(navController: NavController) {
    val recordList = listOf(
        RideRecord("1", "11-20", "周四", 52.5, 15, "单人"),
        RideRecord("2", "11-20", "周四", 52.5, 15, "单人"),
        RideRecord("3", "11-20", "周四", 52.5, 15, "单人"),
        RideRecord("4", "11-20", "周四", 52.5, 15, "单人"),
        RideRecord("5", "11-20", "周四", 52.5, 15, "单人"),
        RideRecord("6", "11-20", "周四", 52.5, 15, "单人"),
        RideRecord("7", "11-20", "周四", 52.5, 15, "单人"),
    )
    EBikeMileagePage(navController, recordList)
}

@Composable
fun EBikeMileagePage(navController: NavController, recordList: List<RideRecord>) {
    LazyColumn(modifier = Modifier.statusBarsPadding()) {
        item {
            EBikeHeader {
                navController.popBackStack()
            }
        }
        item {
            MonthSelector()
        }

        items(items = recordList, key = { it.id }) { record ->
            RideRecordCard(record)
        }
    }

}

@Composable
@Preview(showBackground = true)
fun EBikePagePreview() {
    val recordList = listOf(
        RideRecord("1", "11-20", "周四", 52.5, 15, "单人"),
        RideRecord("2", "11-20", "周四", 52.5, 15, "单人"),
        RideRecord("3", "11-20", "周四", 52.5, 15, "单人"),
        RideRecord("4", "11-20", "周四", 52.5, 15, "单人"),
        RideRecord("5", "11-20", "周四", 52.5, 15, "单人"),
        RideRecord("6", "11-20", "周四", 52.5, 15, "单人"),
        RideRecord("7", "11-20", "周四", 52.5, 15, "单人"),
    )
    val navController = rememberNavController()
    EBikeMileagePage(navController, recordList)
}

@Preview(showBackground = true)
@Composable
fun EBikeHeader(onBack: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .padding(start = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "back",
            modifier = Modifier.clickable { onBack() })

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

@Composable
fun RideRecordCard(item: RideRecord) {

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
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
                    Icon(SunIcon, contentDescription = null)
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
    val id: String,
    val date: String,
    val weekday: String,
    val km: Double,
    val temp: Int,
    val type: String,
)


