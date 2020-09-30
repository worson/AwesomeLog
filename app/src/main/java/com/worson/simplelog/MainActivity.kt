package com.worson.simplelog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.worson.simplelog.R
import com.worson.lib.log.L
import com.worson.lib.log.SimpleLog

class MainActivity : AppCompatActivity() {

    val  TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SimpleLog.initConsolePrint(true)
        L.i(TAG, "onCreate: ")
        L.d(TAG) {"onCreate: "}

    }
}