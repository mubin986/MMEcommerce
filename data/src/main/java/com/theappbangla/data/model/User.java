package com.theappbangla.data.model;

public class User {
    public String ref;
    public String name;
    public String phone;
    public String orderRefs;
    public String paymentRefs;
    public Long timestamp;

    private String fcmToken;

    public String getFcmToken() {
        return fcmToken;
    }

}
