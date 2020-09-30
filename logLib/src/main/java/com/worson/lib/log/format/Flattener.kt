package com.langogo.lib.log.format

import com.worson.lib.log.LogItem

/**
 * 说明:
 * @author worson  07.15 2020
 */
interface Flattener {

    fun flatten(item: LogItem): String
}