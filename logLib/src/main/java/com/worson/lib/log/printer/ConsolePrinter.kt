package com.langogo.lib.log.printer

import com.worson.lib.log.LogItem
import com.langogo.lib.log.format.BasicFlattener
import com.langogo.lib.log.format.Flattener

/**
 * 说明:打印日志空实现
 * @author worson  07.15 2020
 */
class NoActionPrinter(val formater: Flattener = BasicFlattener()) :
    Printer() {

    override fun println(item: LogItem) {

    }

    override fun flush(type:Int) {

    }
}