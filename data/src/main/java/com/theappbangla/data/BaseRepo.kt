package com.theappbangla.data

import android.arch.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

abstract class BaseRepo {

    val NODE_SHOE = "p_shoe"
    val NODE_CLOTH = "p_cloth"

    val KEY_USER = "user"
    val KEY_ORDER = "order"
    val KEY_PAYMENT = "payment"
    val KEY_CATEGORY = "category"
    val KEY_PRODUCT = "product"

    var fs : FirebaseFirestore
    var user : FirebaseUser?

    var userRef : CollectionReference
    var orderRef : CollectionReference
    var paymentRef: CollectionReference
    var categoryRef: CollectionReference
    var productRef: CollectionReference

    init {
        fs = FirebaseFirestore.getInstance()
        user = FirebaseAuth.getInstance().currentUser

        userRef = fs.collection(KEY_USER)
        orderRef = fs.collection(KEY_ORDER)
        paymentRef = fs.collection(KEY_PAYMENT)
        categoryRef = fs.collection(KEY_CATEGORY)
        productRef = fs.collection(KEY_PRODUCT)
    }

}