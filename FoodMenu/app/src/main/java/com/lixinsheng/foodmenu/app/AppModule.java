package com.lixinsheng.foodmenu.app;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lixinsheng on 16/1/11.
 */
@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication(){
        return application;
    }


    @Provides
    @Singleton
    SharedPreferences provideSp(){
        return PreferenceManager.getDefaultSharedPreferences(application);
    }



}
