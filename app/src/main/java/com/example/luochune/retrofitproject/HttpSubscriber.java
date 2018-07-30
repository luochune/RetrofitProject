package com.example.luochune.retrofitproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.retrofithttprequest.BaseHttpSubscriber;
import com.example.retrofithttprequest.NetCallBack;
import com.example.retrofithttprequest.utils.NetworkUtil;

/**
 * Created by luochune on 2018/6/11
 */
public abstract class  HttpSubscriber<T> extends BaseHttpSubscriber<T>{
    private Context mContext;
    public HttpSubscriber(Context mContext, NetCallBack<T> netCallBack) {
        super(netCallBack);
        this.mContext=mContext;
    }
//TODO 可以根据code值做统一处理
    @Override
    public void loadFailData(int code, String msg) {
        super.loadFailData(code, msg);
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT);
    }

    @Override
    public boolean hasNetWork() {
        return NetworkUtil.isNetworkConnected(mContext);
    }

    @Override
    public void noNet() {
        super.noNet();
        Toast.makeText(mContext,"请先连接网络!",Toast.LENGTH_SHORT).show();
    }
}
