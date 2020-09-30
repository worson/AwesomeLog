package com.langogo.lib.log.printer.file.backup

import java.io.File




/**
 * 说明:
 * @author worson  07.17 2020
 */
interface BackupStrategy {

    fun shouldBackup(file: File): Boolean
}