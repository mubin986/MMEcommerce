package com.theappbangla.data.model;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

public class BaseProduct {
    public String ref;
    public String title;
    public String description;

    public Long timestamp = System.currentTimeMillis();
    public int availableQuantity;
    public String sellerRef;
    public String photo;
    public int price;

    @Exclude
    public boolean isAddToCart = false;

    private Map<String, String> catMap = new HashMap<>();
    private Map<String, String> brandMap = new HashMap<>();

    public void setBrand(Brand brand) {
        this.brandMap = getModifiedMap(brand);
    }

    @Exclude
    public String getBrandName() {
        return brandMap.get(Constants.KEY_NAME);
    }

    @Exclude
    public String getBrandRef() {
        return brandMap.get(Constants.KEY_REF);
    }

    public void setCategory(Category category) {
        this.catMap = getModifiedMap(category);
    }

    @Exclude
    public String getCategoryRef() {
        return catMap.get(Constants.KEY_REF);
    }

    @Exclude
    public String getCategoryName() {
        return catMap.get(Constants.KEY_NAME);
    }

    public Map<String, String> getCatMap() {
        return catMap;
    }

    public Map<String, String> getBrandMap() {
        return brandMap;
    }

    private Map<String, String> getModifiedMap(BaseType baseType) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.KEY_REF, baseType.ref);
        map.put(Constants.KEY_NAME, baseType.name);
        return map;
    }
}