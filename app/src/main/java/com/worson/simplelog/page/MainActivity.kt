package com.worson.simplelog.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.worson.simplelog.R
import com.worson.lib.log.L


class MainActivity : AppCompatActivity() {

    val  TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        L.i(TAG, "onCreate: ")
        L.d(TAG) {"onCreate: "}

    }





}