package com.theappbangla.data.admin

import android.arch.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class AdminViewModel : ViewModel() {

    private val TAG = "Admin_View_Model"

    private val adminRepository = AdminRepository()
    private val items = emptyArray<Any>()

    fun saveItem(item: Any, callback: Callback<String>) {
        adminRepository.addItem(item)
            .addOnSuccessListener { callback.onSuccess(null) }
            .addOnFailureListener { callback.onError() }
    }

    fun test(item: Any, callback: Callback<String>) {
        FirebaseFirestore.getInstance().collection("temp").document("mubin").set(item)
            .addOnSuccessListener { callback.onSuccess(null) }
            .addOnFailureListener { callback.onError() }
    }

    interface Callback<T> {
        fun onSuccess(result: T?)
        fun onError()
    }
}