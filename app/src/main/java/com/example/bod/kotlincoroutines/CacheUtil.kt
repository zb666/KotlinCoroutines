package com.example.bod.kotlincoroutines


import com.blankj.utilcode.util.Utils
import timber.log.Timber
import java.io.File
import java.security.MessageDigest


private val String.md5: String
    get() {
        val md = MessageDigest.getInstance("MD5")
        val hash = md.digest(this.toByteArray())
        val str = StringBuilder()
        for (i in hash.indices) {
            val v = hash[i].toInt() and 0xFF
            if (v < 16) str.append(0)
            str.append(v.toString(16))
        }
        return str.toString()
    }

private val String.cacheFileName: String
    get() {
        val index = this.indexOfLast { it == '.' }
        return if (index > 0) {
            substring(0, index).md5 + substring(index)
        } else {
            md5
        }
    }

val File.cacheTempFile: File
    get() = File(this.parent, ".temp/${this.name}").also {
        if (!it.parentFile.exists()) {
            it.parentFile.mkdirs()
        }
    }

val String.cacheFile: File
    get() {
        return File(Utils.getApp().cacheDir, cacheFileName)
    }


