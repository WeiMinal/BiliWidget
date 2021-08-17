package com.weiminal.biliwidget

import kotlin.Throws
import org.json.JSONException
import org.json.JSONObject
import com.weiminal.biliwidget.DataToJson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader
import java.lang.StringBuilder
import java.net.URL
import java.nio.charset.Charset

object DataToJson {
    @Throws(IOException::class)
    private fun str(r: Reader): String {
        val sb = StringBuilder()
        var a: Int
        while (r.read().also { a = it } != -1) {
            sb.append(a.toChar())
        }
        return sb.toString()
    }

    @Throws(IOException::class, JSONException::class)
    fun getData(url: String?): JSONObject {
        val `is` = URL(url).openStream()
        return try {
            val br = BufferedReader(
                InputStreamReader(
                    `is`,
                    Charset.forName("UTF-8")
                )
            )
            val jsonText = str(br)
            JSONObject(jsonText)
        } finally {
            `is`.close()
        }
    }
}