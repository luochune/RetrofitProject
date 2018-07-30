package com.example.retrofithttprequest;

import android.util.Log;

import com.google.gson.Gson;

import javax.xml.transform.Transformer;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Anthony on 2016/11/28.
 * Class Note:
 */

public class RxUtils {

    public static <T> ObservableTransformer<T, T> all_io() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.observeOn(Schedulers.io()).subscribeOn(Schedulers.io());
            }
        };
    }

    public static <T> ObservableTransformer<T, T> defaultSchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            }
        };
    }
  /*  public static <T> ObservableTransformer<String, T> handleResult() {   //compose判断结果
        return httpResponseObservable -> httpResponseObservable.flatMap(new Function<String, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(String result) throws Exception {
                try
                {
                    HttpResult<T> httpResult=new Gson().fromJson(result,HttpResult.class);
                    int flag=httpResult.getFlag();
                    if (flag==200) {
                        return createData(httpResult.getRsObj());
                    }else if(flag==400||flag==100)
                    {
                        Log.i("token:error","需要重新登录");
                        return Observable.error(new ApiException("重新登录"));
                    }else {
                        return Observable.error(new ApiException("服务器返回error"));
                    }
                }catch (Exception error)
                {
                    return Observable.error(new ApiException(error.getMessage()));
                }
            }

        });
    }*/
    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    /*public static <T> Observable.Transformer<HttpResult<T>, T> handleResult() {   //compose判断结果
        return httpResponseObservable -> httpResponseObservable.flatMap(new Func1<HttpResult<T>, Observable<T>>() {
            @Override
            public Observable<T> call(HttpResult<T> result) {
                int flag=result.getFlag();
                if (flag==200) {
                    return createData(result.getRsObj());
                }else if(flag==400||flag==100)
                {
                    Log.i("token:error","需要重新登录");
                    return Observable.error(new ApiException("重新登录")
                }else {
                    return Observable.error(new ApiException("服务器返回error"));
                }
            }
        });
    }*/

    /**
     * 生成Observable
     *
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData(final T t) {
      return Observable.create(new ObservableOnSubscribe<T>() {
          @Override
          public void subscribe(ObservableEmitter<T> emitter) throws Exception {
              emitter.onNext(t);
              emitter.onComplete();
          }
      });
    }

}
