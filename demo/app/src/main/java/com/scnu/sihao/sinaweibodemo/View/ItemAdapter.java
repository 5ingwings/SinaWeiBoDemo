package com.scnu.sihao.sinaweibodemo.View;

import android.content.ClipData;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.scnu.sihao.sinaweibodemo.Model.entity.ItemModel;
import com.scnu.sihao.sinaweibodemo.Utils.MyComponent;
import com.scnu.sihao.sinaweibodemo.R;
import com.scnu.sihao.sinaweibodemo.ViewModel.ItemViewModel;
import com.scnu.sihao.sinaweibodemo.databinding.ItemLayoutBinding;

import java.net.URL;
import java.util.ArrayList;


// 创建ItemAdapter
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

private static ItemViewModel aItemViewModel;
    public static String[] mScreen_name=new String[ItemViewModel.COUNT];
    public static String[] mCreated_at=new String[ItemViewModel.COUNT];
    public static String[] mSource=new String[ItemViewModel.COUNT];
    public static String[] mReposts_count=new String[ItemViewModel.COUNT];
    public static String[] mComments_count=new String[ItemViewModel.COUNT];
    public static String[] mAttitudes_count=new String[ItemViewModel.COUNT];
    public static String[] mText=new String[ItemViewModel.COUNT];
    public static String[] mProfile_image_url=new String[ItemViewModel.COUNT];
public static String[][] mPicture=new String[ItemViewModel.COUNT][9];
    // 构造方法 传入数组
    public ItemAdapter(String[] screen_name,String[] created_at,String[] source,
                       String[] reposts_count,String[] comments_count,String[] attitudes_count,
                       String[] text,String[] profile_image_url,
                       String[][] picture) {
            mScreen_name=screen_name;
            mCreated_at=created_at;
            mSource=source;
            mReposts_count=reposts_count;
            mComments_count=comments_count;
            mAttitudes_count=attitudes_count;
            mText=text;
            mProfile_image_url=profile_image_url;
            mPicture=picture;
    }

    /*public void updateData(String[ screenName) {
        this.mScreenName = screenName;
        notifyDataSetChanged();
    }*/


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // 传入itemViewModel给MyComponent
         MyComponent.getObject3(aItemViewModel);

        ItemLayoutBinding itemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_layout,
                parent, false,new MyComponent());
        return new ViewHolder(itemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // 传过来的ArrayList 根据position绑定值    position为当前界面显示的item条目  从0开始 若有多个则调用多个 但是也是一个个按先后顺序的
         holder.bindItem(position);
        Log.i("position", String.valueOf(position));
    }
    // 重写该方法  可以实现设置item显示数量 不用调用 重写了就自动执行
    @Override
    public int getItemCount() {
        // 获取item的数量
        return ItemViewModel.COUNT;

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemLayoutBinding mItemLayoutBinding;
        public ViewHolder(ItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.itemLayout);
            this.mItemLayoutBinding = itemLayoutBinding;
        }

        void bindItem(final int bindPosition){

            mItemLayoutBinding.setItemModel(new ItemModel
                                             (mScreen_name[bindPosition],mCreated_at[bindPosition],
                                              mSource[bindPosition],mReposts_count[bindPosition],
                                              mComments_count[bindPosition],mAttitudes_count[bindPosition],
                                              mText[bindPosition],mProfile_image_url[bindPosition]));
            mItemLayoutBinding.setNinePictureArray(mPicture);
            mItemLayoutBinding.setPosition(bindPosition);

        }
    }
    public static void getObject(ItemViewModel itemViewModel){
        aItemViewModel=itemViewModel;
    }
}