package com.weiminal.biliwidget.utils

import okhttp3.*
import java.io.IOException

class NetUtil {

    companion object {

        fun get(url: String, userAgent: String, cookie: String): String {
            var returnBody = "none"
            try {
                val okHttpClient = OkHttpClient()
                val mRequest: Request = Request.Builder()
                    .url(url)
                    .addHeader("Referer", "https://www.bilibili.com/")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("User-Agent", userAgent)
                    .addHeader("Cookie", cookie)
                    .get()
                    .build()
                val mCall: Call = okHttpClient.newCall(mRequest)
                val mResponse: Response = mCall.execute()
                val mResponseBody: String? = mResponse.body?.string()
                if (mResponseBody != null) {
                    returnBody = mResponseBody
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return returnBody
        }

        fun post(
            url: String,
            userAgent: String,
            cookie: String,
            reequestBody: RequestBody
        ): String {
            var returnBody = "none"
            try {
                val okHttpClient = OkHttpClient()
                val mRequest: Request = Request.Builder()
                    .url(url)
                    .addHeader("Referer", "https://www.bilibili.com/")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("User-Agent", userAgent)
                    .addHeader("Cookie", cookie)
                    .post(reequestBody)
                    .build()
                val mCall: Call = okHttpClient.newCall(mRequest)
                val mResponse: Response = mCall.execute()
                val mResponseBody: String? = mResponse.body?.string()
                if (mResponseBody != null) {
                    returnBody = mResponseBody
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return returnBody
        }
    }
}