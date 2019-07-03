package com.theappbangla.data.model;

public class Shoe extends BaseProduct {

    public static final String ORDER_BY_TIMESTAMP = "timestamp";

    public String size;
    public int sizeCode;

    public Shoe() {}

    public Shoe(String title, int price) {
        this.title = title;
        this.price = price;
    }

}
