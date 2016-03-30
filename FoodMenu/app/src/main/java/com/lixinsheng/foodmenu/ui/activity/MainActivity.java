package com.lixinsheng.foodmenu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.lixinsheng.foodmenu.R;
import com.lixinsheng.foodmenu.app.AppComponent;
import com.lixinsheng.foodmenu.base.BaseActivity;
import com.lixinsheng.foodmenu.bean.Classify;
import com.lixinsheng.foodmenu.commen.InitiateSearch;
import com.lixinsheng.foodmenu.ui.activity.component.DaggerMainActivityComponent;
import com.lixinsheng.foodmenu.ui.activity.module.MainActivityModule;
import com.lixinsheng.foodmenu.ui.activity.presenter.MainActivityPresenter;
import com.lixinsheng.foodmenu.ui.adapter.SearchAdpater;
import com.lixinsheng.foodmenu.ui.view.GameCenterViewGroup;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by lixinsheng on 16/1/12.
 */
public class MainActivity extends BaseActivity {

    @Inject
    MainActivityPresenter presenter;

//    @Bind(R.id.toolbar)
//    Toolbar toolbar;

    @Bind(R.id.search_img)
    ImageView search_img;

//    @Bind(R.id.classify_list)
//    RecyclerView classify_list;

    @Bind(R.id.card_search)
    CardView card_search;

    //搜索列表
    @Bind(R.id.listView)
    ListView listView;
    //搜索框
    @Bind(R.id.edit_text_search)
    EditText edit_text_search;

    @Bind(R.id.line_divider)
    View line_divider;
    //搜索模块回退按钮
    @Bind(R.id.image_search_back)
    ImageView image_search_back;
    //清除搜索框文字按钮
    @Bind(R.id.clearSearch)
    ImageView clearSearch;
    //球形显示
    @Bind(R.id.ball_groups)
    GameCenterViewGroup ball_group;

    //背景图片
    @Bind(R.id.de_img_backgroud)
    ImageView de_img_backgroud;

    Drawable bac_drawable;

    @Bind(R.id.changeBalls)
    LinearLayout changeBalls;


//    private ClassifyAdapter adapter;

    private SearchAdpater searchAdpater;

    private InitiateSearch initiateSearch;

    private ArrayList<Classify> list = new ArrayList<>();
    private ArrayList<Classify> choosedlist = new ArrayList<>();
    private ArrayList<Classify> searchlist = new ArrayList<>();
    private ArrayList<String> namelist = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void doBusiness(Context mContext) {
//        ball_group.setTexts(Arrays.asList("1","2","3","4","5"));
        presenter.getData();
        initiateSearch = new InitiateSearch();
        searchAdpater = new SearchAdpater(searchlist);
        listView.setAdapter(searchAdpater);
        //事件操作
        operaActions();

        handleBackAnimate();
    }

    private void handleBackAnimate() {
        bac_drawable = de_img_backgroud.getDrawable();

        Observable.timer(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).map(new Func1<Long, Object>() {
            @Override
            public Object call(Long aLong) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate_anim);
                de_img_backgroud.startAnimation(animation);
                return null;
            }
        }).subscribe();

    }

    public void setClassify(ArrayList<Classify> list) {
        this.list = list;

//        adapter = new ClassifyAdapter(list);
//        adapter.setOnRecyclerViewListener(this);
//        classify_list.setLayoutManager(new GridLayoutManager(this, 2));
//        classify_list.setAdapter(adapter);
    }

    public void setChoosedClassfy(ArrayList<Classify> choosedlist) {
        this.choosedlist = choosedlist;
        ball_group.setVisibility(View.VISIBLE);
        ball_group.setTexts(presenter.setBallText(choosedlist));
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
    }

//    @Override
//    public void onItemClick(int position) {
//        Intent intent = new Intent(MainActivity.this,FoodMenuActivity.class);
//        intent.putExtra("id",list.get(position).getId());
//        startActivity(intent);
//    }
//
//
//    @Override
//    public boolean onItemLongClick(int position) {
//        Log.i("OIUY", "false position: " + position);
//        return false;
//    }

    private void operaActions() {
        RxView.clicks(search_img)
                .compose(this.<Void>bindToLifecycle())
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                        initiateSearch.handleToolBar(MainActivity.this, card_search, listView, edit_text_search, line_divider, ball_group);

                    }
                });

        RxView.clicks(image_search_back)
                .compose(this.<Void>bindToLifecycle())
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                        initiateSearch.handleToolBar(MainActivity.this, card_search, listView, edit_text_search, line_divider, ball_group);

                    }
                });

        RxView.clicks(clearSearch)
                .compose(this.<Void>bindToLifecycle())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        edit_text_search.setText("");
                    }
                });


        Observable<TextViewTextChangeEvent> editTextSearchObservable =
                RxTextView.textChangeEvents(edit_text_search).skip(1);

        RxView.clicks(changeBalls)
                .compose(this.<Void>bindToLifecycle())
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        ball_group.setTexts(presenter.handleData(list));
                    }
                });

        RxTextView.textChangeEvents(edit_text_search)
                .compose(this.<TextViewTextChangeEvent>bindToLifecycle())
                .debounce(400, TimeUnit.MILLISECONDS)
                .skip(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TextViewTextChangeEvent>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(TextViewTextChangeEvent onTextChangeEvent) {
                        if (onTextChangeEvent.text().toString().length() < 1) {
                            searchlist.clear();
                            searchAdpater.notifyDataSetChanged();
                            return;
                        }
                        if (list != null && list.size() > 0) {
                            searchlist.clear();
                            for (int i = 0; i < list.size(); i++) {
                                int index = list.get(i).getName().indexOf(onTextChangeEvent.text().toString());
                                if (index != -1) {
                                    searchlist.add(list.get(i));
                                }
                            }
                            searchAdpater.notifyDataSetChanged();
                        }
                    }
                });


//        edit_text_search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        ball_group.setOnMenuItemClickListener(new GameCenterViewGroup.OnMenuItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                Intent intent = new Intent(MainActivity.this, FoodMenuActivity.class);
                intent.putExtra("id", choosedlist.get(pos).getId());
                intent.putExtra("title", choosedlist.get(pos).getName());
                startActivity(intent);
            }
        });

    }
}
