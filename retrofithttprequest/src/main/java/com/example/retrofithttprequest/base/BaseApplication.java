package com.example.retrofithttprequest.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by luochune on 2018/6/7
 */
public class BaseApplication extends Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
    public static Context getContext() {
        return mContext;
    }

}
