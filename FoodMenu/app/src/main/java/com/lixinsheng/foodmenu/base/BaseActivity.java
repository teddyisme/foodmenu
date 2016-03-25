package com.lixinsheng.foodmenu.base;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lixinsheng.foodmenu.R;
import com.lixinsheng.foodmenu.app.AppApplication;
import com.lixinsheng.foodmenu.app.AppComponent;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by lixinsheng on 16/1/12.
 */
public abstract class BaseActivity extends RxAppCompatActivity implements IBaseActivity{

    private View mContextView = null;

//    @Inject
//    protected SharedPreferences mSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ((AppApplication)getApplication()).getAppComponent().inject(this);

        mContextView = LayoutInflater.from(this).inflate(bindLayout(), null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus();
        }
        setStatusColor(Color.TRANSPARENT);
        setContentView(mContextView);


        initView(mContextView);

        ButterKnife.bind(this);

        setupActivityComponent(AppApplication.get().getAppComponent());

        doBusiness(this);
    }
    /**
     * 设置通知栏颜色
     *
     * @param color
     */
    protected void setStatusColor(int color) {
        if (color != -1) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(color);//通知栏所需颜色
        }
    }

    @TargetApi(19)
    protected void setTranslucentStatus() {
        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//    window.setFlags(
//      WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
//      WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resume();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void initView(View view) {
    }

    @Override
    public void resume() {

    }

    protected abstract void setupActivityComponent(AppComponent appComponent);
}
