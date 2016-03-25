package com.lixinsheng.foodmenu.bean;

/**
 * Created by lixinsheng on 16/2/16.
 */
public class FoodMenu {

    //    count: 6662
//    description: 7）加盖，置于蒸锅内，隔水以文火炖2个小时，以表面呈现少量泡沫，有点沸腾、粘稠感和蛋清香味为蒸好的标准
//    fcount: 0
//    food: 干血燕燕窝,冰糖
//    id: 183
//    images:
//    img: /cook/150802/1340f07baad474a757825191701d5e1e.jpg
//    keywords: 燕窝 纯净水 膨胀 冰糖 清洗
//    name: 冰糖燕窝
//    rcount: 0

//    http://tnfs.tngou.net/image

    private Integer count;
    private String description;
    private Integer fcount;
    private String food;
    private Integer id;
    private String img;
    private String keywords;
    private String name;
    private Integer rcount;


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFcount() {
        return fcount;
    }

    public void setFcount(Integer fcount) {
        this.fcount = fcount;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return "http://tnfs.tngou.net/image" + img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRcount() {
        return rcount;
    }

    public void setRcount(Integer rcount) {
        this.rcount = rcount;
    }
}

