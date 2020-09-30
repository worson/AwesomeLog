package com.langogo.lib.log.printer.file

import java.text.SimpleDateFormat
import java.util.*


/**
 * 说明:
 * @author worson  07.17 2020
 */
interface FileNameGenerator {

    fun generateFileName(logLevel: Int, timestamp: Long): String
}


class DateFileNameGenerator :
    FileNameGenerator {
    var mLocalDateFormat: SimpleDateFormat = SimpleDateFormat("yyyy_MM_dd___HH_mm_ss", Locale.US)


    override fun generateFileName(logLevel: Int, timestamp: Long): String {
        val sdf = mLocalDateFormat
        sdf.timeZone = TimeZone.getDefault()
        return "log__${sdf.format(Date(timestamp))}.log"
    }
}