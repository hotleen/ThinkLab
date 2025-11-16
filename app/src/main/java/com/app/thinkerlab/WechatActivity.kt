package com.app.thinkerlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.thinkerlab.ui.components.WeBottomTab
import com.app.thinkerlab.ui.components.WeViewModel

class WechatActivity : ComponentActivity() {

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                val viewModel = viewModel<WeViewModel>()
                val pagerState = rememberPagerState(initialPage = 0) { 4 }
                HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
                    when (page) {
                        0 -> ChatList()
                        1 -> Box(Modifier.fillMaxSize())
                        2 -> Box(Modifier.fillMaxSize())
                        3 -> Box(Modifier.fillMaxSize())
                    }
                }
                WeBottomTab(viewModel.selectedTab) {
                    viewModel.selectedTab = it
                }
            }
        }
    }

    // compose UI组件按照实体命名，即Pascal大写命名
    @Composable
    private fun ChatList() {
        Box(Modifier.fillMaxSize())
    }


}