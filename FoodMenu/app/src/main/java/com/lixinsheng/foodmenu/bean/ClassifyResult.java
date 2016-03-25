package com.lixinsheng.foodmenu.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixinsheng on 16/2/14.
 */
public class ClassifyResult {
    private Boolean status;

    public ArrayList<Classify> getTngou() {
        return tngou;
    }

    public void setTngou(ArrayList<Classify> tngou) {
        this.tngou = tngou;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    private ArrayList<Classify> tngou;

}
