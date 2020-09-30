package com.langogo.lib.log.internal



/**
 * 说明:
 * @author worson  07.17 2020
 */
object SystemCompat {
    /**
     * The line separator of system.
     */
    val lineSeparator: String = Platform.get()
        .lineSeparator()
}