package com.app.thinkerlab.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.thinkerlab.R

@Composable
fun PageHeader() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.Red),
            contentAlignment = Alignment.CenterStart
        ) {
            Icon(
                painterResource(R.drawable.back_arrow),
                "back",
                modifier = Modifier
                    .padding(start = 20.dp)
                    .size(24.dp),
                Color.Black
            )
        }
    }
}

@Composable
fun ThinkInputItem(
    inputLabel: String,
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier,
    underlineColor: Color = Color.Gray,
    underlineThickness: Dp = 1.dp
) {
    Column(modifier) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = inputLabel,
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(end = 8.dp)
            )
            BasicTextField(
                value = text,
                onValueChange = onTextChange,
                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                cursorBrush = SolidColor(Color.Black),
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(underlineThickness)
                .background(underlineColor)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewThinkInputItem() {
    var text by remember { mutableStateOf("") }

    ThinkInputItem(
        inputLabel = "miles",
        "thinkInput",
        onTextChange = { text = it },
        underlineColor = Color.Blue,
        underlineThickness = 2.dp,
        modifier = Modifier.padding(16.dp)
    )
}


@Composable
@Preview(showBackground = true)
fun PageHeaderPreview() {
    PageHeader()
}