package com.lixinsheng.foodmenu.commen;

import android.support.v4.content.ContextCompat;

import com.lixinsheng.foodmenu.app.AppApplication;

/**
 * Created by lixinsheng on 16/2/15.
 */
public class Utills {

    public static int getResourceColor(int color) {
        return ContextCompat.getColor(AppApplication.get(), color);
    }
}
