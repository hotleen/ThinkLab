package com.app.thinkerlab.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.app.thinkerlab.R
import kotlinx.coroutines.launch

class DualRecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dual_record)

        val viewModel = ViewModelProvider(this).get(DualRecordViewModel::class.java)

        viewModel.startCallTimer()

        // 👇 关键代码在这里
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // 通话时长
                launch {
                    viewModel.callTimeText.collect { time ->
                        Log.i("testHzy", "call time: $time")
                    }
                }

                // 当前系统时间
                launch {
                    viewModel.currentTimeText.collect { time ->
                        Log.i("testHzy", "current time: $time")
                    }
                }
            }
        }
    }
}