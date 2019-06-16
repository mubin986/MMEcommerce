package com.theappbangla.mmecommerce.data.model

data class Category (
    var title: String,
    var description: String
) {
    var ref: String? = null
    var productRefs: List<String> = emptyList()
}