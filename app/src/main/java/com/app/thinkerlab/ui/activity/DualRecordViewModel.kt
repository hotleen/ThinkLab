package com.app.thinkerlab.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class DualRecordViewModel : ViewModel() {

    private val _seconds = MutableLiveData(0)

    val seconds: LiveData<Int> = _seconds

    fun startTimer() {
        viewModelScope.launch {
            while (isActive) {
                delay(1000)
                _seconds.value = _seconds.value?.plus(1)
            }
        }
    }
}