package com.example.luochune.retrofitproject;

import android.content.Context;

import com.example.retrofithttprequest.NetCallBack;
import com.example.retrofithttprequest.RxUtils;
import com.example.retrofithttprequest.base.BaseDataRepository;

import java.util.HashMap;

import static com.example.luochune.retrofitproject.BuildConfig.BaseUrl;

/**
 * Created by luochune on 2018/6/7
 */
public class DataRepository extends BaseDataRepository {
    private Context mContext;

    public DataRepository(Context mContext) {
        super(mContext);
        this.mContext = mContext;
    }

    /* public void getAppVersion(String platform, NetCallBack<String> netCallBack) {
         String url = BaseUrl + "release/version/release/featchInfo.do";
         HashMap<String, String> params = new HashMap<String, String>();
         params.put("platform_type", platform);
         getmRetrofitHelper().getApi(AppConfigApi.class).postMapParamsRequest(url, params).compose(RxUtils.<String>defaultSchedulers()).subscribe(new HttpSubscriber<String>(netCallBack) {
             @Override
             public void onNext(String s) {
                 netCallBack.onSuccess(s);
             }
         });
     }*/
    /*  public void getNovelListData()
      {

      }*/
    public void getNovelListData(String novelName,NetCallBack<NovelBean> novelBeanNetCallBack)
    {
        String url=BaseUrl+"novelSearchApi";
        HashMap<String,String> map=new HashMap<String,String>();
        map.put("fname",novelName);
        getmRetrofitHelper().getApi(RemoteApi.class).postString(url,map).compose(RxUtils.defaultSchedulers()).map(new HttpResultFilter<NovelBean>(NovelBean.class)).subscribe(new HttpSubscriber<NovelBean>(mContext,novelBeanNetCallBack) {
            @Override
            public void onNext(NovelBean novelBean) {
                novelBeanNetCallBack.onSuccess(novelBean);
            }
        });
    }
    /*  public void getB2bIndexData(String token, NetCallBack<B2BIndexBean> netCallBack) {
        String url = BaseUrl + "app/b2bIndex.do";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("token", token);
       getmRetrofitHelper().getApi(RemoteApi.class).postString(url, map).compose(RxUtils.defaultSchedulers()).map(new HttpResultFilter<B2BIndexBean>()).subscribe(new HttpSubscriber<B2BIndexBean>(mContext,netCallBack)
        {
            @Override
            public void onNext(B2BIndexBean s) {
               // Log.i("返回json:\n",s);
                netCallBack.onSuccess(s);
            }
        });*/
   // }
  /*  public void getB2bIndexData(String token, NetCallBack<B2BIndexBean> netCallBack) {
        String url = BaseUrl + "app/b2bIndex.do";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("token", token);
        getmRetrofitHelper().getApi(RemoteApi.class).postString(url, map).compose(RxUtils.defaultSchedulers()).map(json->{
            B2BIndexBean b2BIndexBean=new GsonResponsePasare<B2BIndexBean>(){}.deal(json);
            return b2BIndexBean;
        }).subscribe(new HttpSubscriber<B2BIndexBean>(mContext,netCallBack)
        {
            @Override
            public void onNext(B2BIndexBean s) {
               // Log.i("返回json:\n",s);
                netCallBack.onSuccess(s);
            }
        });
    }*/
    public void getCusRelatedCops(String token,NetCallBack<String> netCallBack)
    {
        String url=BaseUrl+"app/cust/serving/getCustRelateCorps.do";
        HashMap<String,String> map=new HashMap<String,String>();
        map.put("token",token);
       /* getmRetrofitHelper().getApi(RemoteApi.class).postString(url,map).compose(RxUtils.defaultSchedulers()).subscribe(new HttpSubscriber<String>(netCallBack) {
            @Override
            public void onNext(String s) {
                netCallBack.onSuccess(s);
            }
        });*/
    }
}

