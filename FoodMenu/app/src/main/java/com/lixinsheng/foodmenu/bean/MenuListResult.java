package com.lixinsheng.foodmenu.bean;

import java.util.ArrayList;

/**
 * Created by lixinsheng on 16/2/16.
 */
public class MenuListResult {

    private Boolean status;
    private Integer total;
    private ArrayList<FoodMenu> tngou;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public ArrayList<FoodMenu> getTngou() {
        return tngou;
    }

    public void setTngou(ArrayList<FoodMenu> tngou) {
        this.tngou = tngou;
    }
}
