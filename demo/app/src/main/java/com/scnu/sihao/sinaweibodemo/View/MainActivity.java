package com.scnu.sihao.sinaweibodemo.View;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scnu.sihao.sinaweibodemo.R;
import com.scnu.sihao.sinaweibodemo.ViewModel.ItemViewModel;
import com.scnu.sihao.sinaweibodemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static ItemViewModel itemViewModel2;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);// 不显示程序的标题栏 继承Activity才可以生效

        //改写 setContentView 进行LoginActivity和activity_login的连接
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //通过ID去绑定XML的组件
        mRecyclerView = activityMainBinding.recyclerView;
        //调用mainViewModel 去载入数据

        itemViewModel2.initData(MainActivity.this);
        // 加载布局管理器
        initView();
    }

    public static void getObject(ItemViewModel itemViewModel){
        itemViewModel2=itemViewModel;
    }
    private void initView() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        //设置adapter
        mRecyclerView.setAdapter(itemViewModel2.mAdapter);
        //添加分割线
        mRecyclerView.addItemDecoration(new ItemDivideLineView(
                this, DividerItemDecoration.VERTICAL));
    }
}