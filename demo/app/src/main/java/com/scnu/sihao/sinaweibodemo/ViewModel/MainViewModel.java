package com.scnu.sihao.sinaweibodemo.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.scnu.sihao.sinaweibodemo.Model.data.RetrofitFactory;
import com.scnu.sihao.sinaweibodemo.View.ItemAdapter;
import com.scnu.sihao.sinaweibodemo.Model.data.HttpService;
import com.scnu.sihao.sinaweibodemo.Model.entity.ItemModel;
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
 * Created by SiHao on 2017/11/28.
 *
 */

public class MainViewModel {

}
