package com.app.thinkerlab.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DualRecordViewModel : ViewModel() {

    /* =======================
     * 1️⃣ 1 秒节拍（共享）
     * ======================= */

    private val secondTicker = flow {
        while (true) {
            emit(Unit)
            delay(1_000)
        }
    }.shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        replay = 0
    )

    /* =======================
     * 2️⃣ 通话时长
     * ======================= */

    private val _callSeconds = MutableStateFlow(0L)

    val callTimeText: StateFlow<String> =
        _callSeconds
            .map { seconds -> formatCallTime(seconds) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = "00:00:00"
            )

    private var timerJob: Job? = null

    fun startCallTimer() {
        if (timerJob != null) return   // 防止重复启动

        timerJob = viewModelScope.launch {
            secondTicker.collect {
                _callSeconds.value += 1
            }
        }
    }

    fun stopCallTimer() {
        timerJob?.cancel()
        timerJob = null
        _callSeconds.value = 0
    }

    /* =======================
     * 3️⃣ 当前系统时间
     * ======================= */

    private val dateTimeFormat =
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    val currentTimeText: StateFlow<String> =
        secondTicker
            .map { formatCurrentTime() }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = formatCurrentTime()
            )

    /* =======================
     * 4️⃣ 格式化函数
     * ======================= */

    private fun formatCallTime(seconds: Long): String {
        val h = seconds / 3600
        val m = (seconds % 3600) / 60
        val s = seconds % 60

        return String.format("%02d:%02d:%02d", h, m, s)
    }

    private fun formatCurrentTime(): String {
        return dateTimeFormat.format(Date())
    }
}
