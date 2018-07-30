package com.example.retrofithttprequest;

/**
 * Created by luochune on 2018/6/8
 */
public abstract class NetCallBack<T> {
   public abstract void onSuccess(T successObj);
   public abstract void  onFail(String msgStr);
}
