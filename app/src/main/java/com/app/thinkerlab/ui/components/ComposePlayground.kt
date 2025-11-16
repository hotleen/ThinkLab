package com.app.thinkerlab.ui.components

import android.service.autofill.OnClickAction
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonExample() {
    var isOpenDialog by remember { mutableStateOf(false) }
    Button(
        onClick = { isOpenDialog = true },
        colors = ButtonDefaults.textButtonColors(containerColor = Color.Red)
    ) {
        Text("Compose Button")
    }
    if (isOpenDialog) {
        AlertDialog(
            onDismissRequest = { isOpenDialog = false },  // 点击外部或返回键时关闭
            title = { Text(text = "提示") },
            text = { Text("确定要删除这条记录吗？") },
            confirmButton = {
                TextButton(onClick = {
                    isOpenDialog = false
                    // 执行确认逻辑
                }) {
                    Text("确定")
                }
            },
            dismissButton = {
                TextButton(onClick = { isOpenDialog = false }) {
                    Text("取消")
                }
            }
        )
    }
}

@Composable
fun SwitchExample() {
    var switchState by remember { mutableStateOf(true) }
    Switch(checked = switchState, onCheckedChange = { switchState = it })
}



@Composable
fun DropdownMenuExample() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("A", "B", "C", "D", "E", "F")
    val disabledValue = "B"
    var selectedIndex by remember { mutableIntStateOf(0) }
    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.TopStart)) {

        Text(
            items[selectedIndex],
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .background(Color.Gray)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth().background(
                Color.Red)
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(
                    text = {
                        Text(text = s + if (s == disabledValue) " (Disabled)" else "")
                    },
                    onClick = {
                        if (s != disabledValue) {
                            selectedIndex = index
                            expanded = false
                        }
                    },
                    enabled = s != disabledValue
                )
            }
        }}
}

@Preview
@Composable
fun PreviewDropdownMenuExample() {
    DropdownMenuExample()
}

@Preview
@Composable
fun PreviewSwitchExample() {
    SwitchExample()
}

@Preview(showBackground = true)
@Composable
fun PreviewButtonExample() {
    ButtonExample()
}
