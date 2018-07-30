package com.example.luochune.retrofitproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.retrofithttprequest.NetCallBack;

import java.util.List;

import static com.example.luochune.retrofitproject.BuildConfig.BaseUrl;

public class MainActivity extends BaseActivity {

    private TextView tv;
    private TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.id_tv_result);
        tv1=findViewById(R.id.id_tv_result1);
        getNovelData();
        //getData();
       // parseData();
    }

    private void getNovelData() {
        dataRepository.getNovelListData("盗墓笔记", new NetCallBack<NovelBean>() {
            @Override
            public void onSuccess(NovelBean successObj) {
                if(successObj!=null)
                {
                    String str= successObj.getData().get(0);
                    tv.setText(str);
                }else
                {
                    tv.setText("数据为空");
                }
            }

            @Override
            public void onFail(String msgStr) {
              tv.setText(msgStr);
            }
        });
    }

    private void parseData() {
        List<DataInfo> resultList = new GsonResponsePasare<List<DataInfo>>() {
        }.deal("{\"status\":-4,\"data\":[{\"name\":\"xiaoxuan948\"},{\"name\":\"coca\"}]}");
        for (DataInfo entity : resultList) {
            Log.e("DataInfo:" + entity.toString(), "Value:" + entity.name);
        }

        GsonResponsePasare<DataInfo> pasare = new GsonResponsePasare<DataInfo>() {
        };
        DataInfo dataInfoResult = pasare.deal("{\"status\":-4,\"data\":{\"name\":\"xiaoxuan948\"}}");
        Log.e("DataInfo:" + dataInfoResult.toString(), "Value:" + dataInfoResult.name);

    }
/*
    private void getData() {
        dataRepository.getB2bIndexData("49c5f12469a1486c94d31951db238f36", new NetCallBack<B2BIndexBean>() {
            @Override
            public void onSuccess(B2BIndexBean successObj) {
                tv.setText(successObj.getAuthorityBuyYsk()+"");
            }

            @Override
            public void onFail(String msgStr) {
               tv.setText(msgStr);
            }
        });
       *//* dataRepository.getCusRelatedCops("49c5f12469a1486c94d31951db238f36", new NetCallBack<String>() {
            @Override
            public void onSuccess(String successObj) {
                tv1.setText(successObj);
            }

            @Override
            public void onFail(String msgStr) {
               tv1.setText(msgStr);
            }
        });*//*

    }*/
}
