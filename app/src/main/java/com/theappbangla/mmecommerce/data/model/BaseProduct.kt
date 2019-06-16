package com.theappbangla.mmecommerce.data.model

abstract class BaseProduct {
    var ref: String? = null
    var timestamp: Long = System.currentTimeMillis()
    var availableQuantity: Int = -1
    var sellerRef: String? = null
    var catRef: String? = null
    var catName: String? = null
    var photo: String? = null

    var price: Int = -1
    var description: String? = null


    private var firstName: String? = null
    private var lastName: String? = null
    var name: String? = null
        get() = firstName + " " + lastName
        set(value) {
            field = value
        }
}