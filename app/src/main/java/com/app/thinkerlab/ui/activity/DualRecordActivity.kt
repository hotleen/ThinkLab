package com.app.thinkerlab.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.app.thinkerlab.R
import com.app.thinkerlab.ui.data.VideoRepositoryProvider
import kotlinx.coroutines.launch

/**
 * Repository 里的网络质量 / 音量回调，怎么通知 Activity？
 * 👉 单向数据流：Repository → ViewModel → Activity
 *
 * Activity 用户点击挂断，怎么调用 SDK？
 * 👉 Activity → ViewModel → Repository → SDK
 */
class DualRecordActivity : AppCompatActivity() {

    private val repository by lazy {
        VideoRepositoryProvider.repository
    }

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

                // 网络质量
                lifecycleScope.launch {
                    repository.observeNetworkQuality()
                        .collect { quality ->
                        }
                }

                // 对方音量
                lifecycleScope.launch {
                    repository.observeRemoteVolume()
                        .collect { volume ->
                        }
                }
            }
        }
    }
}