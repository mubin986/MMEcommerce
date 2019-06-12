package com.theappbangla.mmecommerce.data.model

data class User (
    var ref: String,
    var name: String,
    var phone: String,
    var address: String,
    var fcmToken: String,
    var orderRefs: ArrayList<String>,
    var paymentRefs: ArrayList<String>,
    var timestamp: Long
)