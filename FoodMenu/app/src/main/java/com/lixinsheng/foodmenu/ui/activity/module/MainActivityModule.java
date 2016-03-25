package com.lixinsheng.foodmenu.ui.activity.module;

import com.lixinsheng.foodmenu.ui.activity.ActivityScope;
import com.lixinsheng.foodmenu.ui.activity.MainActivity;
import com.lixinsheng.foodmenu.ui.activity.presenter.MainActivityPresenter;
import com.lixinsheng.foodmenu.app.api.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @ActivityScope
    MainActivity provideMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    MainActivityPresenter provideMainActivityPresenter(MainActivity mainActivity, ApiService apiService) {
        return new MainActivityPresenter(mainActivity,apiService);
    }

}
