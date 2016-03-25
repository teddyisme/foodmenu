package com.lixinsheng.foodmenu.ui.activity.presenter;

import com.lixinsheng.foodmenu.app.api.ApiService;
import com.lixinsheng.foodmenu.bean.MenuListResult;
import com.lixinsheng.foodmenu.ui.activity.FoodMenuActivity;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by lixinsheng on 16/2/16.
 */
public class FoodMenuActivityPresenter {
    private FoodMenuActivity foodMenuActivity;
    private ApiService apiService;

    private Integer page = 1;

    public FoodMenuActivityPresenter(FoodMenuActivity foodMenuActivity, ApiService apiService) {
        this.foodMenuActivity = foodMenuActivity;
        this.apiService = apiService;
    }

    public void getOneClassifyFoodList(Integer id) {
        final Map<String, String> map = new HashMap<String, String>();
        map.put("id", id.toString());
        map.put("page", page.toString());
        map.put("rows", "20");
        apiService.getOneClassifyFoodList(map)
                .compose(foodMenuActivity.<MenuListResult>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MenuListResult>() {
                    @Override
                    public void call(MenuListResult menuListResult) {
                        foodMenuActivity.setListData(menuListResult.getTngou());
                    }
                });
    }

    public void addPage(){
        page++;
    }

}
