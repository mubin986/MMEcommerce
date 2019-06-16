package com.theappbangla.mmecommerce.data.model

data class User (
    var name: String,
    var phone: String
) {
    var ref: String? = null
    var address: String? = null
    var fcmToken: String? = null
    var orderRefs: ArrayList<String>? = null
    var paymentRefs: ArrayList<String>? = null
    var timestamp: Long = System.currentTimeMillis()
}