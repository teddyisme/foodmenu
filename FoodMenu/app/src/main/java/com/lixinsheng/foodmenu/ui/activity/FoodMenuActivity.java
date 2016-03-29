package com.lixinsheng.foodmenu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jakewharton.rxbinding.view.RxView;
import com.lixinsheng.foodmenu.R;
import com.lixinsheng.foodmenu.app.AppComponent;
import com.lixinsheng.foodmenu.base.BaseActivity;
import com.lixinsheng.foodmenu.base.BaseRecycleAdapter;
import com.lixinsheng.foodmenu.bean.FoodMenu;
import com.lixinsheng.foodmenu.ui.activity.component.DaggerFoodMenuActivityComponent;
import com.lixinsheng.foodmenu.ui.activity.module.FoodMenuActivityModule;
import com.lixinsheng.foodmenu.ui.activity.presenter.FoodMenuActivityPresenter;
import com.lixinsheng.foodmenu.ui.adapter.FoodMenuAdpater;
import com.lixinsheng.foodmenu.ui.view.MyRecycleView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * Created by lixinsheng on 16/2/16.
 */
public class FoodMenuActivity extends BaseActivity implements BaseRecycleAdapter.OnRecyclerViewListener {
    @Inject
    FoodMenuActivityPresenter presenter;

    @Bind(R.id.menulist)
    MyRecycleView menulist;

    @Bind(R.id.back_btn)
    AppCompatButton back_btn;

    @Bind(R.id.title)
    AppCompatTextView title;

    private Integer food_id;
    private String menu_name;

    private FoodMenuAdpater adapter;

    private ArrayList<FoodMenu> list = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_foodmenu;
    }

    @Override
    public void doBusiness(Context mContext) {
        food_id = getIntent().getIntExtra("id", 0);
        menu_name = getIntent().getStringExtra("title");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setCollapsible(true);
        toolbar.setBackgroundResource(R.color.orang);
        setSupportActionBar(toolbar);

        adapter = new FoodMenuAdpater(list, this);
        adapter.setOnRecyclerViewListener(this);
        menulist.setHasFixedSize(true);
        menulist.setLayoutManager(new LinearLayoutManager(this));
        menulist.addItemDecoration(new BaseRecycleAdapter.LineItemDecoration(this,R.drawable.space_line_shape));
        menulist.setAdapter(adapter);
        menulist.setOnScrollBottomListener(new MyRecycleView.onScrollBottomListener() {
            @Override
            public void onBottom() {
                presenter.addPage();
                presenter.getOneClassifyFoodList(food_id);
            }
        });

        RxView.clicks(back_btn)
                .compose(this.<Void>bindToLifecycle())
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        finish();
                    }
                });

        title.setText(menu_name);

        presenter.getOneClassifyFoodList(food_id);
    }

    public void setListData(ArrayList<FoodMenu> list) {
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerFoodMenuActivityComponent.builder()
                .appComponent(appComponent)
                .foodMenuActivityModule(new FoodMenuActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this,FoodDetailActivity.class);
        intent.putExtra("id",list.get(position).getId());
        intent.putExtra("title",list.get(position).getName());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }



        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            if (parent.getChildPosition(view) != 0)
                outRect.top = space;
        }
    }
}
