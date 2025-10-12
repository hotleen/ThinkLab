package com.app.thinkerlab.ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WeViewModel : ViewModel() {

    var selectedTab by mutableIntStateOf(0)  //mutableStateOf 状态容器，状态改变触发compose重回 类似于前端双向绑定
}