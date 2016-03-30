package com.lixinsheng.foodmenu.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jakewharton.rxbinding.view.RxView;
import com.lixinsheng.foodmenu.R;
import com.lixinsheng.foodmenu.app.AppComponent;
import com.lixinsheng.foodmenu.base.BaseActivity;
import com.lixinsheng.foodmenu.bean.FoodDetailResult;
import com.lixinsheng.foodmenu.ui.activity.component.DaggerFoodDetailActivityComponent;
import com.lixinsheng.foodmenu.ui.activity.module.FoodDetailActivityModule;
import com.lixinsheng.foodmenu.ui.activity.presenter.FoodDetailActivityPresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * Created by lixinsheng on 16/2/21.
 */
public class FoodDetailActivity extends BaseActivity {
    @Inject
    FoodDetailActivityPresenter presenter;

    //食物图片
    @Bind(R.id.food_img)
    ImageView food_img;

    //食物名称
    @Bind(R.id.food_name)
    TextView food_name;

    //食物简介
    @Bind(R.id.food_message)
    TextView food_message;

    //菜谱说明
    @Bind(R.id.food_description)
    TextView food_description;

    //菜谱食材
    @Bind(R.id.food_keyWord)
    TextView food_keyWord;

    //菜谱简介块
    @Bind(R.id.food_message_block)
    TextView food_message_block;

    //菜谱描述块
    @Bind(R.id.food_description_block)
    TextView food_description_block;

    @Bind(R.id.back_btn)
    AppCompatButton back_btn;

    @Bind(R.id.title)
    AppCompatTextView title;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerFoodDetailActivityComponent.builder()
                .appComponent(appComponent)
                .foodDetailActivityModule(new FoodDetailActivityModule(this))
                .build()
                .inject(this);


    }

    @Override
    public int bindLayout() {
        return R.layout.activity_food_detail;
    }

    @Override
    public void doBusiness(Context mContext) {
        Integer id = getIntent().getIntExtra("id", 0);

        presenter.getFoodDetail(id);

        RxView.clicks(back_btn)
                .compose(this.<Void>bindToLifecycle())
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        finish();
                    }
                });
        title.setText(getIntent().getStringExtra("title"));
    }

    public void setData(FoodDetailResult data) {
        Glide
                .with(this)
                .load(data.getImg())
                .centerCrop()
                .crossFade()
                .into(food_img);
//                .into(new SimpleTarget<GlideDrawable>() {
//                    @Override
//                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                        food_img.setImageDrawable(resource);
//                    }
//                });

        food_name.setText(data.getName());

        food_message.setText(Html.fromHtml(data.getMessage()));

        food_description.setText(data.getDescription());

        food_keyWord.setText("主要食材: " + data.getFood());

        food_message_block.setVisibility(View.VISIBLE);

        food_description_block.setVisibility(View.VISIBLE);
    }

}
