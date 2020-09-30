package com.worson.lib.log

import com.langogo.lib.log.internal.Platform

/**
 * 说明:
 * @author worson  08.03 2020
 */
object SimpleLog {

    @JvmStatic
    fun initConsolePrint(debug:Boolean){
        L.init(
            LogConfiguration.Builder()
                .logLevel(if (debug) LogLevel.ALL else LogLevel.DEBUG)
                .threadInfo(debug)
                .traceInfo(debug, 7)
                .addPrinter(Platform.get().defaultPrinter())
                .build()
        )
    }
}