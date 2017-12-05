package com.scnu.sihao.sinaweibodemo.Model.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scnu.sihao.sinaweibodemo.Utils.OKHttpUtil;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SiHao on 2017/11/30.
 * 建立retrofit对象
 */

public class RetrofitFactory {
    public static final String baseUrl="https://api.weibo.com";

    public Retrofit retrofit;
    public RetrofitFactory() {

        OKHttpUtil OKHttpUtil = new OKHttpUtil();
        // 创建gson实例
        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        //创建Retrofit实例
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OKHttpUtil.builder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }
}
