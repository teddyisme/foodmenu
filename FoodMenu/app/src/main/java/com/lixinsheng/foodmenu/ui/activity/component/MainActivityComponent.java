package com.lixinsheng.foodmenu.ui.activity.component;



import com.lixinsheng.foodmenu.ui.activity.ActivityScope;
import com.lixinsheng.foodmenu.ui.activity.MainActivity;
import com.lixinsheng.foodmenu.ui.activity.module.MainActivityModule;
import com.lixinsheng.foodmenu.ui.activity.presenter.MainActivityPresenter;
import com.lixinsheng.foodmenu.app.AppComponent;

import dagger.Component;

@ActivityScope
@Component(modules = MainActivityModule.class,dependencies = AppComponent.class)
public interface MainActivityComponent {
    MainActivity inject(MainActivity mainActivity);

    MainActivityPresenter presenter();

}
