package com.example.luochune.retrofitproject;

import android.util.Log;

import com.example.retrofithttprequest.ApiException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;

import io.reactivex.functions.Function;

/**
 * Created by luochune on 2018/6/11
 */
public class HttpResultFilter<T> implements Function<String,T>{
    /**
     * 指定结果集泛型
     */
    private Class<T> classOfT;
    public HttpResultFilter(Class<T> fClassOfT) {
         this.classOfT=fClassOfT;
    }

    @Override
    public T apply(String jsonStr) throws Exception {
        Log.i("返回json",jsonStr);
        JSONObject jsonObject=new JSONObject(jsonStr);
        int code=jsonObject.getInt("code");
        String msg=jsonObject.getString("msg");
        if(code==200)
        {
            //数据请求成功
            String dataJsonStr=jsonObject.getString("data");
            T result=new Gson().fromJson(jsonStr,classOfT);
            return result;
        }else
        {
            throw new ApiException(code,msg);
        }
       // Type jsonType = new TypeToken<BaseNetBean<T>>() {}.getType();
      //  BaseNetBean<T> baseNetBean=new Gson().fromJson(jsonStr,jsonType);
     //TODO    T t=new GsonResponsePasare<T>(){}.deal(jsonStr);
        //TODO

        /* baseNetBean.setFlag(400);
         baseNetBean.setMsg("未登录");*/
        //TODO 测试
       // int code=tBaseNetBean.getFlag();
     /*   if(baseNetBean.getFlag()==200)
        {
            return baseNetBean.getRsObj();
        }else
        {
            throw new ApiException(baseNetBean.getFlag(),baseNetBean.getMsg());
        }*/

    }
  /*  private class ResultFilter<T> implements Func1<HttpBean<T>, T> {
        @Override
        public T call(HttpBean<T> tHttpBean) {
            if (tHttpBean.getStatus() != 1){
                throw new ApiException(tHttpBean.getStatus());
            }
            return tHttpBean.getData();
        }
    }*/

}
