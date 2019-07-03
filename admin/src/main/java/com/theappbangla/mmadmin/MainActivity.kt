package com.theappbangla.mmadmin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.theappbangla.data.admin.AdminViewModel
import com.theappbangla.data.model.Cloth
import com.theappbangla.data.model.Shoe
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "Main_Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adminViewModel = AdminViewModel()

        /*for (i in 0..10) {
            val shoe = Shoe()
            shoe.ref = "shoe_ref_$i"
            shoe.title = "Shoe Title $i"
            shoe.availableQuantity = i + 10
            shoe.price = (i * 100) + 200
            shoe.description = "Description $i"
            shoe.sellerRef = "seller_ref_$i"
            shoe.photo = "https://images-eu.ssl-images-amazon.com/images/I/41QBR7vkqlL._SX395_QL70_.jpg"
            shoe.size = (0..8).random().toString()
            shoe.sizeCode = (30..38).random()

            adminViewModel.saveItem(shoe, object : AdminViewModel.Callback<String> {
                override fun onSuccess(result: String?) {
                    Log.d(TAG, "success: $i")
                }

                override fun onError() {

                }

            })

        }*/

        /*for (i in 0..10) {
            val cloth = Cloth()
            cloth.ref = "cloth_ref_$i"
            cloth.title = "Cloth Title $i"
            cloth.availableQuantity = i + 45
            cloth.price = (i * 50) + 100
            cloth.description = "Description Cloth $i"
            cloth.sellerRef = "cloth_seller_ref_$i"
            cloth.photo = "https://5.imimg.com/data5/VT/DS/MY-17368652/plain-round-neck-tshirt-500x500.jpg"
            cloth.size = (0..8).random().toString()

            adminViewModel.saveItem(cloth, object : AdminViewModel.Callback<String> {
                override fun onSuccess(result: String) {
                    Log.d(TAG, "success: $i")
                }

                override fun onError() {

                }

            })

        }*/


        et_timestamp.setText(System.currentTimeMillis().toString())

        btn_save.setOnClickListener {
            val shoe = Shoe()
            shoe.ref = et_ref.text.toString()
            shoe.title = et_title.text.toString()
            shoe.description = et_description.text.toString()
            shoe.availableQuantity = et_av_quantity.text.toString().toInt()
            shoe.sellerRef = et_seller_ref.text.toString()
            shoe.photo = et_photo.text.toString()
            shoe.price = et_price.text.toString().toInt()

            adminViewModel.saveItem(shoe, object : AdminViewModel.Callback<String> {
                override fun onSuccess(result: String) {
                    showToast("saved!")
                }

                override fun onError() {
                    showToast("failed!")
                }

            })
        }
    }

    fun showToast(msg: String) {
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
    }


}
