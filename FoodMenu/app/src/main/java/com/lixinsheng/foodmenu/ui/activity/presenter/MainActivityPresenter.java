package com.lixinsheng.foodmenu.ui.activity.presenter;

import com.lixinsheng.foodmenu.app.api.ApiService;
import com.lixinsheng.foodmenu.bean.Classify;
import com.lixinsheng.foodmenu.bean.ClassifyResult;
import com.lixinsheng.foodmenu.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lixinsheng on 16/1/12.
 */
public class MainActivityPresenter {

    private MainActivity mainActivity;
    private ApiService apiService;

    public MainActivityPresenter(MainActivity mainActivity, ApiService apiService) {
        this.mainActivity = mainActivity;
        this.apiService = apiService;
    }

    public void getData() {
        final Map<String, String> map = new HashMap<String, String>();
        map.put("id", "0");
        apiService.getMenuClassify(map)
                .compose(mainActivity.<ClassifyResult>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .map(new Func1<ClassifyResult, ArrayList<Classify>>() {
                    @Override
                    public ArrayList<Classify> call(ClassifyResult classifyResult) {
                        if (classifyResult.getStatus()) {
                            return classifyResult.getTngou();
                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Action1<ArrayList<Classify>>() {
                    @Override
                    public void call(ArrayList<Classify> classifies) {
//                        mainActivity.setClassify(classifies);
                        mainActivity.setClassify(classifies);
                        handleData(classifies);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

    }

    //随机选中的5个菜谱类别
    private ArrayList<Classify> choosedClassifies = new ArrayList<>();

    public List<String> handleData(ArrayList<Classify> classifies) {
        choosedClassifies.clear();
        Random random = new Random();

        while (choosedClassifies.size() < 5) {
            int randNum = random.nextInt(classifies.size() - 1);
            if (!choosedClassifies.contains(classifies.get(randNum))) {
                choosedClassifies.add(classifies.get(randNum));
            }
        }
        mainActivity.setChoosedClassfy(choosedClassifies);

        return setBallText(choosedClassifies);
    }

    private List<String> setBallText(ArrayList<Classify> choosedClassifies) {
        List<String> texts = new ArrayList<>();
        for (int i = 0; i < choosedClassifies.size(); i++) {
            texts.add(choosedClassifies.get(i).getName());
        }
        return texts;

    }

}
