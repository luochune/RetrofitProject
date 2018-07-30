package com.example.retrofithttprequest;

import android.net.ConnectivityManager;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class BaseHttpSubscriber<T> implements Observer<T> {
    protected NetCallBack<T> netCallBack;
    public boolean hasNetWork() {
        /*if (!NetworkUtil.isNetworkConnected(BaseApplication.getContext())) {
            Toast.makeText(BaseApplication.getContext(), "请连接网络或稍后重试...", Toast.LENGTH_SHORT).show();
            return false;
        }*/
        return true;
    }

    public BaseHttpSubscriber(NetCallBack<T> netCallBack) {
        this.netCallBack=netCallBack;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (!hasNetWork()) {
            //无网络
            noNet();
        }
    }

    @Override
    public void onComplete() {
        Log.i("RetrofitHelper:","Subscriber On Completed");
    }
  public void noNet()
  {
      Log.i("数据请求","无网络");
  }
    /**
     * 未登录处理
     * @param code
     * @param msg
     */
   public void loadFailData(int code,String msg){
     Log.i("数据加载失败",code+":"+msg);
   }
    @Override
    public void onError(Throwable e) {
        if(e instanceof ApiException)
        {
            int code=((ApiException)e).getCode();
            loadFailData(code,e.getMessage());
        }
        netCallBack.onFail(e.getMessage());
        Log.i("RetrofitHelper:",e.getMessage());
    }
}
