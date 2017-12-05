package com.scnu.sihao.sinaweibodemo.View;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.scnu.sihao.sinaweibodemo.R;
import com.scnu.sihao.sinaweibodemo.ViewModel.LoginViewModel;
import com.scnu.sihao.sinaweibodemo.databinding.ActivityLoginBinding;

/**
 *  LoginActivity
 */
public class LoginActivity extends AppCompatActivity {
    LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //改写 setContentView 进行LoginActivity和activity_login的连接
        ActivityLoginBinding loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        //调用LoginViewModel
         loginViewModel=new LoginViewModel(LoginActivity.this);

        }

        //授权Activity结束后 该Activity打开后，调用该方法，得到回传的数据
        // ************MVVM中可以这样吗 调用authorizeCallBack函数***********************
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //sina login
        if(loginViewModel.mSsoHandler!=null){
            loginViewModel.mSsoHandler.authorizeCallBack(requestCode,resultCode,data);
        }
    }


}
