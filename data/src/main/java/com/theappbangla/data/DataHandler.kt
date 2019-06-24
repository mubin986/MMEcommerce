package com.theappbangla.data

interface DataHandler {

    fun testUpload(data: String, callback: Callback<Void>)

    fun testRead(callback: Callback<String>)

    interface Callback<T> {
        fun onSuccess(result: T?)
        fun onError()
    }
}