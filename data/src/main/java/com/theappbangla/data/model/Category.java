package com.theappbangla.data.model;

public class Category extends BaseType {
    public String additionalInfo;

    public Category() {}

    public Category(String ref, String name) {
        this.ref = ref;
        this.name = name;
    }
}