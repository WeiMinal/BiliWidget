package com.weiminal.biliwidget.utils

import android.util.Log
import org.json.JSONException
import org.json.JSONObject

class ApiUtil {

    companion object {

        fun getInfo(mid: String): JSONObject {
            val infoApi = "https://api.bilibili.com/x/space/acc/info?mid=$mid&jsonp=jsonp"
            val mReturn = NetUtil.get(infoApi, "", "")
            val returnJson = JSONObject()
            returnJson.put("code", "-1")
            if (!mReturn.equals("none")) {
                try {
                    val jsonObject_return = JSONObject(mReturn)
                    val code = jsonObject_return.getString("code")
                    val jsonObject_data = jsonObject_return.getJSONObject("data")
                    val name = jsonObject_data.getString("name")
                    val face = jsonObject_data.getString("face")
                    Log.d("TAG", "getInfo: $name")
                    Log.d("TAG", "getInfo: $face")
                    returnJson.put("code", code)
                    returnJson.put("name", name)
                    returnJson.put("face", face)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            return returnJson

        }

        fun getInfoFollow(mid: String): JSONObject {
            val infoFollowApi = "https://api.bilibili.com/x/relation/stat?vmid=$mid"
            val mReturn = NetUtil.get(infoFollowApi, "", "")
            val returnJson = JSONObject()
            returnJson.put("code", "-1")
            if (!mReturn.equals("none")) {
                try {
                    val jsonObject_return = JSONObject(mReturn)
                    val code = jsonObject_return.getString("code")
                    val jsonObject_data = jsonObject_return.getJSONObject("data")
                    val follower = jsonObject_data.getString("follower")
                    Log.d("TAG", "getInfo: $follower")
                    returnJson.put("code", code)
                    returnJson.put("follower", follower)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            return returnJson

        }
    }
}