package com.example.retrofithttprequest;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by luochune on 2018/6/6
 * retrofit网络请求帮助类
 */
public class RetrofitHelper {
    /**
     * 默认网络请求超时时间
     */
    private static final int DEFAULT_TIMEOUT=30;
    /**
     * 各个数据请求模块接口集合
     */
    private HashMap<String,Object> mServiceMap;
   private Context mContext;
    private String tagName;
    public RetrofitHelper(Context context) {
        mServiceMap=new HashMap<String,Object>();
        mContext=context;
        tagName=this.getClass().getName();
    }
    /**
     * 创建请求serviceapi
     * @param serviceClass Service接口
     * @param <S>
     * @return
     */
    public <S> S getApi(Class<S> serviceClass)
    {
        if(mServiceMap.containsKey(serviceClass.getName()))
        {
            return (S) mServiceMap.get(serviceClass.getName());
        }else
        {
           S serice=createApi(serviceClass);
           mServiceMap.put(serviceClass.getName(),serice);
           return serice;
        }
    }

    /**
     * 创建请求serviceapi
     * @param serviceClass
     * @param <S>
     * @return
     */
    public <S> S createApi(Class<S> serviceClass)
    {
         //自定义okhttp
        OkHttpClient.Builder httpClient=new OkHttpClient.Builder();
        //超时配置
        httpClient.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClient.writeTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS);
        httpClient.readTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS);
        //缓存配置
        File httpCacheDirectory=new File(mContext.getCacheDir(),"okHttpCache");
        //设置最大缓存50M
       httpClient.cache(new Cache(httpCacheDirectory,50*1024*1024));
        //设置网络拦截器
        httpClient.addNetworkInterceptor(new LogInterceptor());
        //请求失败后重试
        httpClient.retryOnConnectionFailure(true);
        //也可设置ssl证书
        return createApi(serviceClass,httpClient.build());
    }

    /**
     * 创建服务api
     * @param serviceClass Service接口名称
     * @param okHttpClient okhttpclient
     * @param <S>
     * @return 创建的serviceapi
     */
    private <S> S createApi(Class<S> serviceClass, OkHttpClient okHttpClient) {
        //在Service类里面定义的局部环境变量
        String baseUrl= "";
        try {
            Field field1 = serviceClass.getField("baseUrl");
            baseUrl = (String) field1.get(serviceClass);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.getMessage();
            e.printStackTrace();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit.create(serviceClass);
    }

    private class LogInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            Log.i(tagName, String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();

            Log.i(tagName, String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    }

}
