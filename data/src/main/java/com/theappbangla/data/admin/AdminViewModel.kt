package com.theappbangla.data.admin

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.theappbangla.data.model.BaseProduct
import com.theappbangla.data.model.Shoe

class AdminViewModel : ViewModel() {

    private val TAG = "Admin_View_Model"

    private val repository = AdminRepository()

    fun saveItem(item: BaseProduct, callback: Callback<String>) {
        if (item.ref.isNotEmpty())
            repository.addItemByRef(item)
                .addOnSuccessListener { callback.onSuccess("") }
                .addOnFailureListener { callback.onError() }

        else
            repository.addItem(item)
            .addOnSuccessListener { callback.onSuccess("") }
            .addOnFailureListener { callback.onError() }
    }

    fun <T : BaseProduct> getItems(cls: Class<out T>, noOfItems: Long, callback: Callback<List<T>>) {
        Log.d("HHHHHH", "mmm")
        repository.getLimitItems(cls, noOfItems)
            .addOnSuccessListener { q ->
                callback.onSuccess(q.toObjects(cls))
            }
            .addOnFailureListener { callback.onError() }
    }

    fun test(callback: Callback<List<Shoe>>) {
        repository.test()
            .addOnSuccessListener { callback.onSuccess(it.toObjects(Shoe::class.java)) }
            .addOnFailureListener { callback.onError() }
    }


    interface Callback<T> {
        fun onSuccess(result: T)
        fun onError()
    }
}