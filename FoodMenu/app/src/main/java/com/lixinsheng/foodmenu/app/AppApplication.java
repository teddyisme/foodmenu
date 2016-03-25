package com.lixinsheng.foodmenu.app;

import android.app.Application;
import android.content.Context;

import com.lixinsheng.foodmenu.app.api.ApiServiceModule;


/**
 * Created by lixinsheng on 16/1/12.
 */
public class AppApplication extends Application {
    private AppComponent appComponent;

    private static Context application;

    public static AppApplication get(){
            return (AppApplication)application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application =  getApplicationContext();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
