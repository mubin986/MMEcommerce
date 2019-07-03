package com.theappbangla.mmecommerce

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.theappbangla.data.admin.AdminViewModel
import com.theappbangla.data.model.BaseProduct
import com.theappbangla.data.model.Cloth
import com.theappbangla.mmecommerce.adapter.KtGenericAdapter
import com.theappbangla.mmecommerce.adapter.KtViewHolderFactory
import com.theappbangla.data.model.Product
import com.theappbangla.data.model.Shoe
import kotlinx.android.synthetic.main.activity_home.*

@Suppress("UNCHECKED_CAST")
class HomeActivity: AppCompatActivity(), KtGenericAdapter.OnItemClickListener<BaseProduct> {

    companion object {
        const val TAG = "HomeActivity"
    }

    private val cartItems = CartDataService.items

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        rv_shoe.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_shoe.adapter = KtGenericAdapter.create(this)

        rv_cloth.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_cloth.adapter = KtGenericAdapter.create(this)
        
        val adminViewModel = AdminViewModel()
        
        adminViewModel.getItems(Shoe::class.java, 20, object : AdminViewModel.Callback<List<Shoe>> {
            override fun onSuccess(result: List<Shoe>) {
                Log.d(TAG, "count : " + result.count().toString())
                (rv_shoe.adapter as KtGenericAdapter<BaseProduct>).setData(result)
            }

            override fun onError() {

            }

        })

        adminViewModel.getItems(Cloth::class.java, 5, object : AdminViewModel.Callback<List<Cloth>> {
            override fun onSuccess(result: List<Cloth>) {
                Log.d(TAG, "count : " + result.count().toString())
                (rv_cloth.adapter as KtGenericAdapter<BaseProduct>).setData(result)
            }

            override fun onError() {

            }

        })


        tv_view_cart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onItemClick(data: BaseProduct) {
        if (cartItems.contains(data)) cartItems.remove(data)
        else cartItems.add(data)

        if (cartItems.size == 1)
            ll_bottom.visibility = View.VISIBLE
        if (cartItems.size == 0)
            ll_bottom.visibility = View.GONE

        tv_no_of_selected_items.setText(cartItems.size.toString())
    }

    fun shoeToast(msg: String) {
        Toast.makeText(this@HomeActivity, msg, Toast.LENGTH_SHORT).show()
    }

}