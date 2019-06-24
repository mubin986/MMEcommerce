package com.theappbangla.mmecommerce

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.theappbangla.data.DataHandler
import com.theappbangla.data.DataHandlerImpl
import com.theappbangla.mmecommerce.adapter.KtGenericAdapter
import com.theappbangla.mmecommerce.adapter.KtViewHolderFactory
import com.theappbangla.data.model.Product
import com.theappbangla.data.model.Shoe
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity: AppCompatActivity() {

    companion object {
        const val TAG = "HomeActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val list= arrayListOf(
            Product("Dog",  2000),
            Product("Cat",  3500)
        )

        val items = arrayListOf<Any>(
            Product("Dog",  2000),
            Shoe("Shoe 1", 400),
            Product("Cat", 3500)
        )

        val layoutManager = LinearLayoutManager(this)
        val adapter = object : KtGenericAdapter<Any>(items) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return KtViewHolderFactory.create(view, viewType)
            }

            override fun getLayoutId(position: Int, obj: Any): Int {
                val layoutID = when(obj) {
                    is Product -> R.layout.item_rv_product
                    is Shoe -> R.layout.temp
                    else -> R.layout.item_rv_product
                }
                return layoutID
            }
        }


        val dataHandler: DataHandler = DataHandlerImpl()

        dataHandler.testUpload("mubin", object: DataHandler.Callback<Void> {
            override fun onSuccess(result: Void?) {
                Log.d(TAG, " testUpload : success")

                rv_product.layoutManager = layoutManager
                rv_product.adapter = adapter
            }

            override fun onError() {
                Log.d(TAG, " testUpload : error")
            }

        })

    }

}