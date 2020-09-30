package com.worson.simplelog

import android.content.Context
import com.langogo.lib.log.internal.Platform
import com.langogo.lib.log.printer.FilePrinter
import com.langogo.lib.log.printer.Printer
import com.langogo.lib.log.printer.file.DateFileNameGenerator
import com.langogo.lib.log.printer.file.handler.ZipLogHandler
import com.langogo.lib.log.printer.file.reporter.LogFileReporter
import com.langogo.lib.log.rpc.SocketSeverPrinterProxy
import com.worson.lib.log.L
import com.worson.lib.log.LogConfiguration
import com.worson.lib.log.LogLevel
import java.io.File

/**
 * 说明:
 * @author worson  09.30 2020
 */
object LogInit {


    fun initFileLog(context: Context,debug:Boolean=true) {
        var filePrinter: Printer?=null
        var logHandler: ZipLogHandler?=null
        var logUploader: LogFileReporter?=null
        val logPath = File(context.cacheDir,"log")
        L.init(
            LogConfiguration.Builder()
                .logLevel(if (debug) LogLevel.ALL else LogLevel.DEBUG)
                .threadInfo(true)
                .traceInfo(debug)
                .addPrinter(Platform.get().defaultPrinter())
                .addPrinter(
                    FilePrinter.Builder(
                        File(logPath.absolutePath, "logging").absolutePath
                    )
                        .fileNameGenerator(DateFileNameGenerator())
                        .logHandler(
                            ZipLogHandler(
                                File(logPath.absolutePath, "backup").absolutePath,
                                limitSize = 100 * 1024 * 1024,
                                reporter = logUploader
                            ).apply {
                                logHandler=this
                            }
                        )
                        .build().apply {
                            filePrinter = this
                        }
                )
                .build()
        )
        filePrinter?.let {
            SocketSeverPrinterProxy(it).start()
        }
    }

     fun initConsoleLog(debug:Boolean=true) {
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