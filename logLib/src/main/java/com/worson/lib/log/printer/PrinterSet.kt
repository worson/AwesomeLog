package com.langogo.lib.log.printer

import com.worson.lib.log.LogItem

/**
 * 说明:
 * @author worson  07.15 2020
 */
class PrinterSet(val printers: Array<Printer>):
    Printer() {

    override fun println(item: LogItem) {
        for (printer in printers) {
            printer.println(item)
        }
    }

    override fun flush(type:Int) {
        for (printer in printers) {
            printer?.flush(type)
        }
    }
}