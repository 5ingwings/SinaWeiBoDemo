package com.scnu.sihao.sinaweibodemo.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.scnu.sihao.sinaweibodemo.Model.data.HttpService;
import com.scnu.sihao.sinaweibodemo.Model.data.RetrofitFactory;
import com.scnu.sihao.sinaweibodemo.View.ItemAdapter;
import com.scnu.sihao.sinaweibodemo.View.MainActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by SiHao on 2017/11/30.
 *
 */

public class ItemViewModel extends BaseObservable {
    public RecyclerView.Adapter mAdapter;
    //LoginActivity.this
    private Context mContext1;
    //MainActivity.this
    private Context mContext2;
    public static int COUNT=8;
    public String str;
    public String[] screen_name=new String[COUNT];
    public String[] created_at=new String[COUNT];
    public String[] mSource=new String[COUNT];
    public String[] source=new String[COUNT];
    public String[] reposts_count=new String[COUNT];
    public String[] comments_count=new String[COUNT];
    public String[] attitudes_count=new String[COUNT];
    public String[] text=new String[COUNT];
    public String[] profile_image_url=new String[COUNT];
    //图片的二维数组 第一维是Item的 第二维是每个Item的多个图片
    public String[][] pic_urls=new String [COUNT][9];
    public boolean canIntent=false;
    public Observable<ResponseBody> call_1;
    public Disposable mDisposable;

    public int mPosition;
    public String[][] NinePicture=new String[COUNT][9];

    private static int count2;
    public void getApi(String accessToken,Context context){
        mContext1=context;

        RetrofitFactory retrofitFactory = new RetrofitFactory();

        // 要在ItemModel中进行网络请求啊！
        //用retrofit创建HttpService对象
        final HttpService httpService = retrofitFactory.retrofit.create(HttpService.class);
        // 接口调用
        call_1= httpService.getAPI(accessToken,COUNT);
        call_1.subscribeOn(Schedulers.io()) //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求结果
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    // 连接成功了就会执行
                    public void onSubscribe(Disposable d) {
                        Log.i("subscribe is ok","subscribe is ok" );
                    }
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Toast.makeText(mContext1, "调用API成功", Toast.LENGTH_SHORT).show();
                        try {
                            str=responseBody.string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.i("str",str);
                        //开始解析

                        try {
                            // 将返回的JSONString格式赋予JSONObject
                            JSONObject jsonObject = new JSONObject(str);
                            // 第一层为statuses 并且statuses为数组形式 因此将其设置为JSONArray形式
                            JSONArray statusesArr = jsonObject.getJSONArray("statuses");
                            // 循环地去获得多条微博
                            // 这里的index+1代表的是第n条status（微博），因此index=0 为第一条 以此类推
                            for (int i = 0; i < COUNT; i++) {
                                // 第一条 下标从0开始
                                JSONObject statusesObj = statusesArr.getJSONObject(i);
                                // statuses中的第一层，即总的第二层
                                // 该条微博的创建时间
                                created_at[i] = statusesObj.getString("created_at");
                                Log.i("created_at", created_at[i]);
                                //该条微博的来源
                                mSource[i] = statusesObj.getString("source");
                                int firstIndex=mSource[i].indexOf(">");
                                int lastIndex=mSource[i].lastIndexOf("<");
                                source[i]=mSource[i].substring(firstIndex+1,lastIndex);
                                Log.i("source", source[i]);
                                //该条微博的转发数
                                reposts_count[i] = statusesObj.getString("reposts_count");
                                Log.i("reposts_count", reposts_count[i]);
                                //该条微博的评论数
                                comments_count[i] = statusesObj.getString("comments_count");
                                Log.i("comments_count", comments_count[i]);
                                //该条微博的点赞数
                                attitudes_count[i] = statusesObj.getString("attitudes_count");
                                Log.i("attitudes_count", attitudes_count[i]);
                                //该条微博的微博text内容
                                text[i] = statusesObj.getString("text");
                                Log.i("text", text[i]);
                                // 总的第三层
                                //获取图片资源的地址 数组形式
                                JSONArray imageArr = statusesObj.getJSONArray("pic_urls");
                                // 循环地去获得多个图片地址
                                // 获得数组的长度
                                //为什么这里是0张？
                                Log.i("图片有多少张", String.valueOf(imageArr.length()));
                                for(int j=0;j<imageArr.length();j++){
                                    JSONObject imageObject = imageArr.getJSONObject(j);
                                    pic_urls[i][j]=imageObject.getString("thumbnail_pic");
                                    Log.i("thumbnail_pic",pic_urls[i][j]);
                                }

                                // 获取user的值
                                String user = statusesObj.getString("user");
                                //user转化为JSONObject
                                JSONObject userObj = new JSONObject(user); // 将其转化为JSONObject
                                //微博主名
                                screen_name[i] = userObj.getString("screen_name"); // 使用getXX方法获取数据
                                Log.i("screen_name", screen_name[i]);
                                //微博主照片的地址
                                profile_image_url[i] = userObj.getString("profile_image_url"); // 使用getXX方法获取数据
                                Log.i("profile_image_url", profile_image_url[i]);
                            }
                            // 若成功传入最后一个微博 指定一个变量 而不是只是一个数值 因为网络请求要花时间
                            if(profile_image_url[COUNT-1]!=null){
                                //若有值
                                canIntent=true;
                                // 非Activity中跳转 满足MVVM吗  不满足要改~~~~~~
                                Intent intent = new Intent(mContext1, MainActivity.class);
                                mContext1.startActivity(intent);
                            }else{
                                canIntent=false;
                            }
                        } catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.i("API调用失败","API调用失败");
                        Toast.makeText(mContext1, "API调用失败", Toast.LENGTH_SHORT).show();
                    }
                    //上游不写complete  成功返回也会自动执行
                    @Override
                    public void onComplete() {
                        Log.i("API调用成功","API调用成功");
                        Toast.makeText(mContext1, "API调用成功", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // 传入数据到Adapter

    public void initData(Context context) {
        mContext2=context;
        mAdapter = new ItemAdapter(screen_name,created_at,source,
                reposts_count,comments_count,attitudes_count,
                text,profile_image_url,pic_urls);
    }

    @BindingAdapter("imageUrl")
    public void loadImageForUrl(ImageView imageView, String url) {
        if(url!=null) {
            Log.i("setImageUrl", String.valueOf(url));
            Log.d("setImageUrl", "setImageUrl is Called");
            //这个值的获取是通过XML使用app:imageUrl的组件去调用这个方法 传入这个组件 再传入get到的url给glide 所以不需要findID
            Glide.with(imageView.getContext()).load(url).into(imageView);
        }
    }
}
