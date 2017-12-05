package com.scnu.sihao.sinaweibodemo.ViewModel;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.scnu.sihao.sinaweibodemo.Model.entity.ItemModel;
import com.scnu.sihao.sinaweibodemo.View.ItemAdapter;
import com.scnu.sihao.sinaweibodemo.View.MainActivity;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

/**
 * Created by SiHao on 2017/11/28.
 * 进行新浪微博授权SDK设置
 */

public class LoginViewModel {
    public SsoHandler mSsoHandler;
    // 这个是封装了access_token remind_in  expires_in等的 但返回的不是JSON格式的
    private Oauth2AccessToken mAccessToken;
    private String accessToken;
    // 先将封装的mAccessToken转为String格式
    private String str_mAccessToken;

    // context
    private Context mContext;

    public LoginViewModel(Context context){
        //做网络请求我们必须把Retrofit Service返回的Observable绑定到Context的生命周期上，
        // 防止在请求回来时Activity已经销毁等异常，其实这个Context的目的就是把网络请求绑定到当前页面的生命周期中
        // 以及给SDK授权提供界面连接
        this.mContext=context;

        // 初始化微博授权SDK
        initWeiBoSDK();
        // context实现子类有：Application、Activity和Service
        //Activity.getApplicationContext获取当前Activity所在的(应用)进程的Context对象
        // Activity.this 返回当前的Activity实例
        // 这里传递context为LoginActivity.this 但是要声明context为Activity的形式
        mSsoHandler = new SsoHandler((Activity) context);
        //设置授权方式 并且建立监听
        loginIn();
    }

    // 初始化SDK
    private void initWeiBoSDK() {
        //新浪微博初始化，对应的参数分别是app_key,回调地址，和权限
        WbSdk.install(mContext, new AuthInfo(mContext, "118543205", "https://api.weibo.com/oauth2/default.html",
                "email,direct_messages_read,direct_messages_write,"
                        + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                        + "follow_app_official_microblog," + "invitation_write"));
        Log.i("initWeiBoSDK","initWeiBoSDK");
    }

    /**
     *       Web+SSO授权方式获取权限
     */
    private void loginIn() {
        mSsoHandler.authorize(new SelfWbAuthListener());
        Log.i("loginIn","loginIn");
    }

    private class SelfWbAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener {
        @Override
        public void onSuccess(final Oauth2AccessToken token) {
                    mAccessToken = token;
                    if (mAccessToken.isSessionValid()) {
                        Log.i("获取mAccessToken成功","获取mAccessToken成功");
                        str_mAccessToken=String.valueOf(mAccessToken);
                        Log.i("mAccessToken为:", str_mAccessToken);

                        //截出access_token
                        int firstIndex=str_mAccessToken.indexOf("access_token");
                        int lastIndex=str_mAccessToken.indexOf(",",firstIndex);
                        Log.i("equalIndex", String.valueOf(firstIndex));
                        if(firstIndex!=-1) {
                            accessToken = str_mAccessToken.substring(firstIndex + 14,lastIndex );
                        }
                    }

                        if (accessToken != null) {
                            Log.i("can go to","can go to");

                            // 调用MainViewModel 去进行网络请求获取API
                            //*******************MVVM是这样吗？********************
                            ItemViewModel itemViewModel = new ItemViewModel();
                            itemViewModel.getApi(accessToken,mContext);

                            //传递itemViewModel给MAinActivity
                            MainActivity.getObject(itemViewModel);
                            //传递itemViewModel给ItemAdapter
                            ItemAdapter.getObject(itemViewModel);
                        }else{
                            Log.i( "cant go to","did not go to retrofitUtil");
                    }
        }

        @Override
        public void cancel() {
            Log.i("取消授权","取消授权");
        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
            Log.i("授权失败","授权失败");
        }
    }

}
