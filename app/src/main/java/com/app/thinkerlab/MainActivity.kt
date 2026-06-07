package com.app.thinkerlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.thinkerlab.ui.components.HomePage
import com.app.thinkerlab.ui.components.MainBottomBar
import com.app.thinkerlab.ui.components.ProfilePage
import com.app.thinkerlab.ui.components.FilePage

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Preview
    @Composable
    fun PreviewMainScreen() {
        MainScreen()
    }
}

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    var showFilePage by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            if (!showFilePage) {
                MainBottomBar(
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it }
                )
            }
        }
    ) { innerPadding ->
        when {
            showFilePage -> FilePage(
                modifier = Modifier.padding(innerPadding),
                onBack = { showFilePage = false }
            )
            else -> when (selectedTab) {
                0 -> HomePage(
                    modifier = Modifier.padding(innerPadding),
                    onCloudDriveClick = { showFilePage = true }
                )
                1 -> ProfilePage(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}
