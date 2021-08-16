package com.weiminal.biliwidget;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class DataToJson {

    /** 数据获取 1.0
     *
     * by WeiMinal **/

    public DataToJson(){

    }

    private static String str(Reader r) throws IOException {
        StringBuilder sb = new StringBuilder();
        int a;
        while ((a = r.read()) != -1) {
            sb.append((char) a);
        }
        return sb.toString();
    }

    public static JSONObject getData(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = str(br);
            return new JSONObject(jsonText);
        }
        finally {
            is.close();
        }
    }
}
