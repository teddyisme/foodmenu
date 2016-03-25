package com.lixinsheng.foodmenu.ui.activity.component;

import com.lixinsheng.foodmenu.app.AppComponent;
import com.lixinsheng.foodmenu.app.api.ApiService;
import com.lixinsheng.foodmenu.commen.Utills;
import com.lixinsheng.foodmenu.ui.activity.ActivityScope;
import com.lixinsheng.foodmenu.ui.activity.FoodMenuActivity;
import com.lixinsheng.foodmenu.ui.activity.MainActivity;
import com.lixinsheng.foodmenu.ui.activity.module.FoodMenuActivityModule;
import com.lixinsheng.foodmenu.ui.activity.presenter.FoodMenuActivityPresenter;
import com.lixinsheng.foodmenu.ui.activity.presenter.MainActivityPresenter;

import dagger.Component;

/**
 * Created by lixinsheng on 16/2/16.
 */
@ActivityScope
@Component(modules = FoodMenuActivityModule.class,dependencies = AppComponent.class)
public interface FoodMenuActivityComponent {

    FoodMenuActivity inject(FoodMenuActivity foodMenuActivity);

    FoodMenuActivityPresenter presenter();

}
