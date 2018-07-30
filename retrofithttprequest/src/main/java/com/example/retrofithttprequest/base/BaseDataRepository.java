package com.example.retrofithttprequest.base;

import android.content.Context;

import com.example.retrofithttprequest.RetrofitHelper;

/**
 * Created by luochune on 2018/6/7
 */
public class BaseDataRepository {
    private RetrofitHelper mRetrofitHelper;
    protected Context mContext;

    public BaseDataRepository(Context mContext) {
        this.mContext = mContext;
        mRetrofitHelper=new RetrofitHelper(mContext);
    }

    public RetrofitHelper getmRetrofitHelper() {
        return mRetrofitHelper;
    }


}

