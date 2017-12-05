package com.scnu.sihao.sinaweibodemo.Model.data;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by SiHao on 2017/11/21.
 * HTTP接口
 */

public interface HttpService {
        // 指引用户授权
       /* @GET("authorize?client_id=118543205&response_type=code&redirect_uri=https://api.weibo.com/oauth2/default.html")
        Call<ResponseBody> getPermission();*/
       // 别漏了这个
/*       @FormUrlEncoded
       @POST("oauth2/access_token?")
       //通过@Field来指定key，后面跟上value
       Observable<AccessTokenBean> getAccessToken(@Field("client_id") String client_id, @Field("client_secret") String client_secret,
                                                  @Field("grant_type") String grant_type, @Field("redirect_uri") String redirect_uri,
                                                  @Field("code") String code);
             */

       //调用API
        @GET("2/statuses/home_timeline.json?")
        Observable<ResponseBody> getAPI(@Query("access_token") String accessToken, @Query("count") int count);

}
