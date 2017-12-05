package com.scnu.sihao.sinaweibodemo.Model.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
/**
 * Created by SiHao on 2017/11/29.
 *
 */

public class ItemModel extends BaseObservable{

    public String myScreen_name;
    public String myCreated_at;
    public String mySource;
    public String myReposts_count;
    public String myComments_count;
    public String myAttitudes_count;
    public String myText;
    public String myProfile_image_url;

    public ItemModel(String Screen_name,String Created_at,String Source,
                     String Reposts_count,String Comments_count,String Attitudes_count,
                     String Text,String Profile_image_url){

        this.myScreen_name=Screen_name;
        this.myCreated_at=Created_at;
        this.mySource=Source;
        this.myReposts_count=Reposts_count;
        this.myComments_count=Comments_count;
        this.myAttitudes_count=Attitudes_count;
        this.myText=Text;
        this.myProfile_image_url=Profile_image_url;

    }

    @Bindable
    public String getScreenName(){
        Log.i("2222",myScreen_name);
        return myScreen_name;
    }

    @Bindable
    public String getCreateAt(){

        Log.i("2222",myCreated_at);
        return myCreated_at;
    }
    @Bindable
    public String getSource(){
        Log.i("2222",mySource);
        return mySource;
    }
    @Bindable
    public String getBodyText(){

        return myText;
    }
    @Bindable
    public String getRepostsCount(){
        return myReposts_count;
    }
    @Bindable
    public String getCommentsCount(){
        return myComments_count;
    }
    @Bindable
    public String getAttitudesCount(){
        return myAttitudes_count;
    }
    @Bindable
    public String getUserImage()  {
        Log.i("profile_image_ur2222", String.valueOf(myProfile_image_url));
        return myProfile_image_url;
    }


}
