package com.lixinsheng.foodmenu.ui.activity.module;

import com.lixinsheng.foodmenu.app.api.ApiService;
import com.lixinsheng.foodmenu.ui.activity.ActivityScope;
import com.lixinsheng.foodmenu.ui.activity.FoodMenuActivity;
import com.lixinsheng.foodmenu.ui.activity.presenter.FoodMenuActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lixinsheng on 16/2/16.
 */

@Module
public class FoodMenuActivityModule {
    private FoodMenuActivity foodMenuActivity;

    public FoodMenuActivityModule(FoodMenuActivity foodMenuActivity) {
        this.foodMenuActivity = foodMenuActivity;
    }

    @Provides
    @ActivityScope
    FoodMenuActivity provideFoodMenuActivity(){
        return foodMenuActivity;
    }

    @Provides
    @ActivityScope
    FoodMenuActivityPresenter provideFoodMenuActivityPresenter(FoodMenuActivity foodMenuActivity, ApiService apiService) {
        return new FoodMenuActivityPresenter(foodMenuActivity,apiService);
    }

}
