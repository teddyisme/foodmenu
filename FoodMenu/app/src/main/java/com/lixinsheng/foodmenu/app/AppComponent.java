package com.lixinsheng.foodmenu.app;

import android.app.Application;

import com.lixinsheng.foodmenu.app.api.ApiService;
import com.lixinsheng.foodmenu.app.api.ApiServiceModule;
import com.lixinsheng.foodmenu.base.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lixinsheng on 16/1/11.
 */
@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class})
public interface AppComponent {

    Application getApplication();

    ApiService getApiService();



}
