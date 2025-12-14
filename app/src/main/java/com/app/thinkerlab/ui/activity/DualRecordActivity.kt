package com.app.thinkerlab.ui.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.thinkerlab.R

class DualRecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dual_record)

        val viewModel = ViewModelProvider(this).get(DualRecordViewModel::class.java)

        viewModel.startTimer()

        viewModel.seconds.observe(this) { seconds ->
            Log.i("testHzy", "second value from viewModel: $seconds")
        }
    }
}