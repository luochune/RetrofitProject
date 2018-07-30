package com.example.luochune.retrofitproject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by luochune on 2018/7/30
 */
public class JsonUtils {
    /*
     *获取json字符串与key对应的json字符串
     */
    public static String getJsontoString(String string, String key) {
        String s = "";
        JSONObject json = null;
        try {
            json = new JSONObject(string);
            if (json.has(key)) {
                s = json.getString(key);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            s = "";
        }
        return s;
    }
}
