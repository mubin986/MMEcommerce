package com.theappbangla.data.model;

public class Brand extends BaseType {
    public String address;
    public Double rating;

    public Brand() {}

    public Brand(String ref, String name) {
        this.ref = ref;
        this.name = name;
    }
}
