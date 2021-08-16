package com.weiminal.biliwidget;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class dataGet {

    /** 数据获取 1.0
     *
     * by WeiMinal **/

    public dataGet(){

    }
    public static String dataShow() throws IOException, JSONException{
        try{
            //api获取数据
            JSONObject json = DataToJson.getData("https://api.bilibili.com/x/relation/stat?vmid=35606358");

            return json.get("data")+"";

        }
        catch (Exception e){
            e.printStackTrace();
            return "无法连接网络";
        }
    }
}