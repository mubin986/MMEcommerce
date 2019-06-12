package com.theappbangla.mmecommerce.data.model

data class Product (
    var ref: String,
    var title: String,
    var description: String,
    var price: String,
    var timestamp: Long,
    var availableQuantity: Int,
    var sellerRef: String,
    var catRef: String
)