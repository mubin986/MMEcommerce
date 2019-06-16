package com.theappbangla.mmecommerce.data.model

data class Product(var title: String): BaseProduct() {
    constructor(title: String, description: String, price: Int):this(title) {
        this.description = description
        this.price = price
    }
}