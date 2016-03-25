package com.lixinsheng.foodmenu.ui.activity.component;

import com.lixinsheng.foodmenu.app.AppComponent;
import com.lixinsheng.foodmenu.ui.activity.ActivityScope;
import com.lixinsheng.foodmenu.ui.activity.FoodDetailActivity;
import com.lixinsheng.foodmenu.ui.activity.module.FoodDetailActivityModule;
import com.lixinsheng.foodmenu.ui.activity.presenter.FoodDetailActivityPresenter;

import dagger.Component;

/**
 * Created by lixinsheng on 16/2/21.
 */
@ActivityScope
@Component(modules = FoodDetailActivityModule.class, dependencies = AppComponent.class)
public interface FoodDetailActivityComponent {
    FoodDetailActivity inject(FoodDetailActivity foodDetailActivity);

    FoodDetailActivityPresenter presenter();


}
