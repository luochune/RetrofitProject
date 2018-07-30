package com.example.luochune.retrofitproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by luochune on 2018/6/7
 */
public class BaseActivity extends Activity {
    public  DataRepository dataRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataRepository=new DataRepository(this);
    }
}
