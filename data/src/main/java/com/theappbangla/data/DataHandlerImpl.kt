package com.theappbangla.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class DataHandlerImpl : DataHandler {

    private companion object {
        const val REF_TEST = "test"
    }

    private var mTestRef: CollectionReference

    init {
        val fs = FirebaseFirestore.getInstance()
        mTestRef = fs.collection(REF_TEST)
    }

    override fun testUpload(data: String, callback: DataHandler.Callback<Void>) {
        val m = HashMap<String, String>()
        m["hi"] = data

        mTestRef.document("t").set(m)
            .addOnSuccessListener { callback.onSuccess(null) }
            .addOnFailureListener { callback.onError() }
    }

    override fun testRead(callback: DataHandler.Callback<String>) {
        mTestRef.document("t").get()
            .addOnSuccessListener { data -> callback.onSuccess(data.get("hi").toString()) }
            .addOnFailureListener { callback.onError() }
    }

    fun test(): LiveData<Boolean> {
        val boo = MutableLiveData<Boolean>()
        boo.value = false

        return boo
    }

}