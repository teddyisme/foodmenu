package com.lixinsheng.foodmenu.ui.activity.presenter;

import android.util.Log;

import com.lixinsheng.foodmenu.app.api.ApiService;
import com.lixinsheng.foodmenu.bean.FoodDetailResult;
import com.lixinsheng.foodmenu.bean.MenuListResult;
import com.lixinsheng.foodmenu.ui.activity.FoodDetailActivity;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by lixinsheng on 16/2/21.
 */
public class FoodDetailActivityPresenter {
    private FoodDetailActivity foodDetailActivity;
    private ApiService apiService;

    public FoodDetailActivityPresenter(FoodDetailActivity foodDetailActivity, ApiService apiService) {
        this.foodDetailActivity = foodDetailActivity;
        this.apiService = apiService;
    }

    public void getFoodDetail(Integer id){
        final Map<String, String> map = new HashMap<String, String>();
        map.put("id", id.toString());
        apiService.getFoodDetail(map)
                .compose(foodDetailActivity.<FoodDetailResult>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FoodDetailResult>() {
                    @Override
                    public void call(FoodDetailResult s) {
                        if(s.getStatus()){
                            foodDetailActivity.setData(s);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.i("IOIO","erro:" + throwable.toString());
                    }
                });


    }



}
