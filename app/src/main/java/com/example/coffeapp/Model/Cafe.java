package com.example.coffeapp.Model;

import java.io.Serializable;

public class Cafe implements Serializable {
    private int id;
    private String name;
    private String price;
    private Integer rate;
    private String img;
    private Boolean like;
    private int so_luong;

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cafe(String name, String price, Integer rate, String img, Boolean like) {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
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
