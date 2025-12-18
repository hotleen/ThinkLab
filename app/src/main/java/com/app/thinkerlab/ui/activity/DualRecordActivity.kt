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

        viewModel.startTimer()

        viewModel.seconds.observe(this) { seconds ->
//            Log.i("testHzy", "second value from viewModel: $seconds")
        }


        // 👇 关键代码在这里
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.callTimeText.collect { timeText ->
                    Log.i("testHzy", "collect value: $timeText")
                }
            }
        }
    }
}