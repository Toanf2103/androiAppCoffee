package com.example.listviewnextlevel;

import android.content.Intent;

import java.text.NumberFormat;
import java.util.Locale;

public class Phone {
    String name,describe;
    Double price;
    Integer image;

    public Phone() {
    }

    public Phone(String name, Double price, Integer image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Phone(String name, Double price, String describe, Integer image) {
        this.name = name;
        this.price = price;
        this.describe = describe;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public  String getPriceString(){
        NumberFormat Us = NumberFormat.getCurrencyInstance(Locale.US);
        return Us.format(this.price);
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
