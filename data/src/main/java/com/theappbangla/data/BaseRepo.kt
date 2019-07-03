package com.theappbangla.data

import android.arch.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.theappbangla.data.model.Cloth
import com.theappbangla.data.model.Shoe

abstract class BaseRepo {

    val ORDER_BY_TIMESTAMP = "timestamp";

    val NODE_SHOE = "p_shoe"
    val NODE_CLOTH = "p_cloth"
    val NODE_OTHER = "p_other"

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

    init {
        fs = FirebaseFirestore.getInstance()
        user = FirebaseAuth.getInstance().currentUser

        userRef = fs.collection(KEY_USER)
        orderRef = fs.collection(KEY_ORDER)
        paymentRef = fs.collection(KEY_PAYMENT)
        categoryRef = fs.collection(KEY_CATEGORY)
    }

}