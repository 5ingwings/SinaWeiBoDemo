package com.scnu.sihao.sinaweibodemo.Utils;

import com.scnu.sihao.sinaweibodemo.ViewModel.ItemViewModel;

/**
 * Created by SiHao on 2017/12/3.
 *
 */

public class MyComponent implements android.databinding.DataBindingComponent{
   private static ItemViewModel myItemViewModel;
    @Override
    public ItemViewModel getItemViewModel() {
        return myItemViewModel;
    }

    public static void getObject3(ItemViewModel itemViewModel){
        myItemViewModel=itemViewModel;
    }

}
