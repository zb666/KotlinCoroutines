package com.example.bod.kotlincoroutines

import android.content.Context
import android.content.res.AssetManager
import android.os.Environment
import android.util.Log
import java.io.*
import java.util.*

/**
 * Utilities class for interacting with the assets and the devices storage to
 * load and save DataSet objects from and to .txt files.
 *
 * @author Philipp Jahoda
 */
object FileUtil {


    private val LOG = "MPChart-FileUtils"

    fun getAPPPath(): String {
        val savepath = (Environment.getExternalStorageDirectory().toString()
                + "/" + AboutMeParams.FILE_NAME_PATH)
        val file = File(savepath)
        if (!file.exists()) {
            file.mkdirs()
        }
        return savepath
    }


}
