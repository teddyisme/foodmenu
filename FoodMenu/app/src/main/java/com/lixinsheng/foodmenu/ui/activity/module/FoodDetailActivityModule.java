package com.lixinsheng.foodmenu.ui.activity.module;

import com.lixinsheng.foodmenu.app.api.ApiService;
import com.lixinsheng.foodmenu.ui.activity.ActivityScope;
import com.lixinsheng.foodmenu.ui.activity.FoodDetailActivity;
import com.lixinsheng.foodmenu.ui.activity.presenter.FoodDetailActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lixinsheng on 16/2/21.
 */
@Module
public class FoodDetailActivityModule {
    public FoodDetailActivityModule(FoodDetailActivity foodDetailActivity) {
        this.foodDetailActivity = foodDetailActivity;
    }

    private FoodDetailActivity foodDetailActivity;


    @ActivityScope
    @Provides
    FoodDetailActivity provideFoodDetailActivity(){return foodDetailActivity;};


    @ActivityScope
    @Provides
    FoodDetailActivityPresenter presenter(FoodDetailActivity foodDetailActivity, ApiService apiService){
        return new FoodDetailActivityPresenter(foodDetailActivity,apiService);
    };




}
