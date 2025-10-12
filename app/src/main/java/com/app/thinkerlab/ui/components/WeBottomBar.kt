package com.app.thinkerlab.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.thinkerlab.R
import com.app.thinkerlab.ui.theme.black
import com.app.thinkerlab.ui.theme.green3
import com.app.thinkerlab.ui.theme.white1

@Composable
fun WeBottomTab(selected: Int, onTabSelected: (Int) -> Unit) {
    Row(Modifier.background(white1)) {
        TabItem(
            if (selected == 0) R.drawable.icon_chat_n else R.drawable.icon_chat_m,
            "聊天",
            if (selected == 0) green3 else black,
            Modifier
                .weight(1f)
                .clickable { onTabSelected(0) }
        )
        TabItem(
            if (selected == 1) R.drawable.icon_address_book_n else R.drawable.icon_address_book_m,
            "通讯录",
            if (selected == 1) green3 else black,
            Modifier
                .weight(1f)
                .clickable { onTabSelected(1) }
        )
        TabItem(
            if (selected == 2) R.drawable.icon_discovery_n else R.drawable.icon_discovery,
            "发现",
            if (selected == 2) green3 else black,
            Modifier
                .weight(1f)
                .clickable { onTabSelected(2) }
        )
        TabItem(
            if (selected == 3) R.drawable.icon_me_n else R.drawable.icon_me_m,
            "我",
            if (selected == 3) green3 else black,
            Modifier
                .weight(1f)
                .clickable { onTabSelected(3) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeTabBarPreview() {
    var selectedTab by remember { mutableIntStateOf(0) }
    WeBottomTab(selectedTab) {
        selectedTab = it
    }
}


@Composable
private fun TabItem(
    @DrawableRes iconId: Int,
    title: String,
    tint: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painterResource(iconId), title, Modifier.size(24.dp), tint = tint)
        Text(title, fontSize = 11.sp, color = tint)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTabItem() {
    TabItem(R.drawable.icon_chat_m, "聊天", tint = MaterialTheme.colorScheme.error)
}