package com.lixinsheng.foodmenu.bean;

/**
 * Created by lixinsheng on 16/2/14.
 */
public class Classify {

//    [{"cookclass":0,"description":"","id":1,"keywords":"","name":"美容","seq":0,"title":""},
//    {"cookclass":0,"description":"","id":10,"keywords":"","name":"减肥","seq":0,"title":""}
//    {"cookclass":0,"description":"","id":15,"keywords":"","name":"保健养生","seq":0,"title":""},
//    {"cookclass":0,"description":"","id":52,"keywords":"","name":"人群","seq":0,"title":""},
//            ,]
    private Integer cookclass;
    private String description;
    private String keywords;
    private String name;
    private Integer id;
    private Integer seq;
    private String title;

    public Integer getCookclass() {
        return cookclass;
    }

    public void setCookclass(Integer cookclass) {
        this.cookclass = cookclass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
