package com.weiminal.biliwidget

import kotlin.Throws
import org.json.JSONException
import org.json.JSONObject
import com.weiminal.biliwidget.DataToJson
import java.io.IOException
import java.lang.Exception

object dataGet {
    @Throws(IOException::class, JSONException::class)
    fun dataShow(): String {
        return try {
            //api获取数据
            val json = DataToJson.getData("https://api.bilibili.com/x/relation/stat?vmid=35606358")
            json["data"].toString() + ""
        } catch (e: Exception) {
            e.printStackTrace()
            "无法连接网络"
        }
    }
}