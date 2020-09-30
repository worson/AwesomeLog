package com.worson.simplelog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.worson.simplelog.R
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

class MainActivity : AppCompatActivity() {

    val  TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        initLogSimple()
        initLogFile()

        L.i(TAG, "onCreate: ")
        L.d(TAG) {"onCreate: "}

    }

    private fun initLogFile() {
        val debug=true
        var filePrinter: Printer?=null
        var logHandler:ZipLogHandler?=null
        var logUploader: LogFileReporter?=null
        val logPath = File(cacheDir.absolutePath,"log")
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

    private fun initLogSimple() {
        val debug=true
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