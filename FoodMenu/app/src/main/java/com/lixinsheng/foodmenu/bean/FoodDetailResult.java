package com.lixinsheng.foodmenu.bean;

/**
 * Created by lixinsheng on 16/3/10.
 */
public class FoodDetailResult {
    private Double count;
    private String description;
    private Double fcount;
    private Double rcount;
    private String food;
    private Integer id;
    private String images;
    private String img;
    private String keywords;
    private String message;
    private String name;
    private Boolean status;
    private String url;

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFcount() {
        return fcount;
    }

    public void setFcount(Double fcount) {
        this.fcount = fcount;
    }

    public Double getRcount() {
        return rcount;
    }

    public void setRcount(Double rcount) {
        this.rcount = rcount;
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
