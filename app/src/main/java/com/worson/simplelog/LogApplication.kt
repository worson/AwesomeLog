package com.worson.simplelog

import android.app.Application

/**
 * 说明:
 * @author worson  09.30 2020
 */
class LogApplication :Application(){

    override fun onCreate() {
        super.onCreate()
//        LogInit.initConsoleLog(true)
        LogInit.initFileLog(this,true)
    }
}