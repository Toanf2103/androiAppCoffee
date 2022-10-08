package com.example.coffeapp.Model;

import java.io.Serializable;

public class Cafe implements Serializable {
    private String name;
    private String price;
    private Integer rate;
    private Integer img;
    private Boolean like;


    public Cafe(String name, String price, Integer rate, Integer img, Boolean like) {
        this.name = name;
        this.price = price;
        this.rate = rate;
        this.img = img;
        this.like = like;
    }

    public Boolean getLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }

    public Cafe() {
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
