package com.worson.simplelog;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.worson.lib.log.L;
import com.worson.lib.log.SimpleLog;

import app.worson.simplelog.R;


public class MainJavaActivity extends AppCompatActivity {

    private final static String TAG = "MainJavaActivityTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java);
        SimpleLog.initConsolePrint(true);
        L.i(TAG, "onCreate: 1");
        L.d(TAG, "onCreate: 2");
    }
}