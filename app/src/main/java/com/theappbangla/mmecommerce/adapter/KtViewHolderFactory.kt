package com.theappbangla.mmecommerce.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.theappbangla.mmecommerce.R
import com.theappbangla.mmecommerce.data.model.Product
import com.theappbangla.mmecommerce.data.model.Shoe
import kotlinx.android.synthetic.main.item_rv_product.view.*
import kotlinx.android.synthetic.main.item_rv_product.view.tv_description
import kotlinx.android.synthetic.main.item_rv_product.view.tv_title
import kotlinx.android.synthetic.main.temp.view.*
import kotlinx.android.synthetic.main.temp.view.tv_price

/**
 * Written by Shariful Islam Mubin [CSE 2K14, KUET]
 * Here I used Factory Method Pattern to create View Holders efficiently without worrying about Adapter
 * create(...) is the Factory Method here. This method is responsible for creating View Holders
 */

object KtViewHolderFactory {

    fun create(view: View, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_rv_product -> ProductViewHolder(view)
            R.layout.temp -> ShoeViewHolder(view)
            else -> {
                ProductViewHolder(view)
            }
        }
    }

    private class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), KtGenericAdapter.Binder<Product> {

        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        override fun bind(product: Product) {
            itemView.tv_title.text = product.title
            itemView.tv_description.text = product.description
            itemView.tv_price.text = product.price.toString()

            itemView.tv_category.text = product.catName ?: ""
            itemView.tv_category.tag = product.catRef ?: ""
            itemView.tag = product.ref ?: ""
        }
    }

    private class ShoeViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView), KtGenericAdapter.Binder<Shoe> {
        override fun bind(data: Shoe) {
            itemView.tv_title.text = data.title
            itemView.tv_price.text = data.price.toString()
        }

    }

    /*private class BusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), KtGenericAdapter.Binder<Bus> {

        var textView: TextView
        init {
            textView = itemView.findViewById(R.id.busName)
        }
        override fun bind(bus: Bus) {
            textView.setText(bus.name)
        }
    }*/
}
