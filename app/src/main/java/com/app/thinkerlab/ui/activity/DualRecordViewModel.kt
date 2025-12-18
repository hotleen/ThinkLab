package com.app.thinkerlab.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class DualRecordViewModel : ViewModel() {

    private val _seconds = MutableLiveData(0)

    val seconds: LiveData<Int> = _seconds

    fun startTimer1() {
        viewModelScope.launch {
            while (isActive) {
                delay(1000)
                _seconds.value = _seconds.value?.plus(1)
            }
        }
    }

    /** 原始计时：秒 */
    private val _callSeconds = MutableStateFlow(0L)

    /** 给 UI 用的格式化时间 */
    val callTimeText: StateFlow<String> =
        _callSeconds
            .map { seconds -> formatTime(seconds) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = "00:00"
            )

    private var timerJob: Job? = null

    fun startTimer() {
        if (timerJob != null) return   // 防止重复启动

        timerJob = viewModelScope.launch {
            while (isActive) {
                delay(1_000)
                _callSeconds.value += 1
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
        _callSeconds.value = 0
    }

    private fun formatTime(seconds: Long): String {
        val h = seconds / 3600
        val m = (seconds % 3600) / 60
        val s = seconds % 60

        return if (h > 0) {
            "%02d:%02d:%02d".format(h, m, s)
        } else {
            "%02d:%02d".format(m, s)
        }
    }
}