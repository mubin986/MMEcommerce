package com.theappbangla.mmadmin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.theappbangla.data.admin.AdminViewModel
import com.theappbangla.data.auth.FirebasePhoneAuth
import com.theappbangla.data.model.BaseProduct
import com.theappbangla.data.model.Shoe

class MainActivity : AppCompatActivity() {

    val TAG = "Main_Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adminViewModel = AdminViewModel()

        val shoe = BaseProduct()
        shoe.title = "Ladies Shoe V"
        shoe.availableQuantity = 19
        shoe.price = 300
        shoe.description = "Awesome Shoe"

        adminViewModel.test(shoe, object : AdminViewModel.Callback<String> {
            override fun onSuccess(result: String?) {
                Log.d(TAG, result)
            }

            override fun onError() { }

        })
    }

}
