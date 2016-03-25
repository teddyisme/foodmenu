package com.lixinsheng.foodmenu.app.api;


import com.lixinsheng.foodmenu.bean.ClassifyResult;
import com.lixinsheng.foodmenu.bean.FoodDetailResult;
import com.lixinsheng.foodmenu.bean.MenuListResult;
import com.lixinsheng.foodmenu.bean.User;

import java.util.List;
import java.util.Map;

import retrofit.http.GET;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by lixinsheng on 16/1/7.
 */
public interface ApiService {
    @GET("users")
    Observable<List<User>> getUserDetailApi(@QueryMap Map<String, String> options);

    @GET("classify")
    Observable<ClassifyResult> getMenuClassify(@QueryMap Map<String, String> options);

    @GET("list")
    Observable<MenuListResult> getOneClassifyFoodList(@QueryMap Map<String, String> options);

    @GET("show")
    Observable<FoodDetailResult> getFoodDetail(@QueryMap Map<String, String> options);
}