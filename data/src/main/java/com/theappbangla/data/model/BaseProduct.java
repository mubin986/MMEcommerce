package com.theappbangla.data.model;

import java.util.HashMap;
import java.util.Map;

public class BaseProduct {
    public String ref;
    public String title;
    public String description;

    public Long timestamp;
    public int availableQuantity;
    public String sellerRef;
    public String photo;
    public int price;

    private Map<String, String> catMap;
    private Map<String, String> brandMap;

    public void setBrand(Brand brand) {
        this.brandMap = getModifiedMap(brand);
    }

    public String getBrandName() {
        return brandMap.get(Constants.KEY_NAME);
    }

    public String getBrandRef() {
        return brandMap.get(Constants.KEY_REF);
    }

    public void setCategory(Category category) {
        this.catMap = getModifiedMap(category);
    }

    public String getCategoryRef() {
        return catMap.get(Constants.KEY_REF);
    }

    public String getCategoryName() {
        return catMap.get(Constants.KEY_NAME);
    }

    private Map<String, String> getModifiedMap(BaseType baseType) {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.KEY_REF, baseType.ref);
        map.put(Constants.KEY_NAME, baseType.name);
        return map;
    }
}