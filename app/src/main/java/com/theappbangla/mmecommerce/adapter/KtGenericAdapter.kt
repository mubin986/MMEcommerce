package com.theappbangla.mmecommerce.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.theappbangla.data.model.BaseProduct
import com.theappbangla.data.model.Product
import com.theappbangla.data.model.Shoe
import com.theappbangla.mmecommerce.R
import kotlinx.android.synthetic.main.item_rv_shoe.view.*

/**
 * Written by Shariful Islam Mubin [CSE 2K14, KUET]
 * Generic RecyclerView Adapter in Kotlin
 */

@Suppress("UNCHECKED_CAST")
abstract class KtGenericAdapter<M> : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    companion object {
        fun <U>create(listener: OnItemClickListener<U>) : KtGenericAdapter<U> {
            return object : KtGenericAdapter<U>() {
                override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                    return KtViewHolderFactory.create(view, viewType)
                }

                override fun getLayoutId(position: Int, obj: U): Int {
                    return when(obj) {
                        is Product -> R.layout.item_rv_base_product
                        is Shoe -> R.layout.item_rv_shoe
                        else -> R.layout.item_rv_base_product
                    }
                }
            }.setOnItemClickListener(listener)
        }
    }

    private var items: List<M>
    private var onItemClickListener: OnItemClickListener<M>? = null

    // abstract methods
    abstract fun getViewHolder(view: View, viewType: Int):RecyclerView.ViewHolder
    protected abstract fun getLayoutId(position: Int, obj: M): Int

    constructor(items: List<M>) {
        this.items = items
    }

    constructor() {
        items = arrayListOf()
    }

    fun setData(items: List<M>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun clearData() {
        items = arrayListOf()
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<M>): KtGenericAdapter<M> {
        this.onItemClickListener = onItemClickListener
        return this
    }

    /*fun addData(item: M) {
        items.add(item)
        notifyItemInserted(items.count() - 1)
    }

    fun removeDataAt(position: Int) {
        items.removeAt(position)
        notifyItemRangeChanged(0, position)
    }*/

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): RecyclerView.ViewHolder {
        return getViewHolder(LayoutInflater.from(viewGroup.context).inflate(type, viewGroup, false), type);
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as Binder<M>).bind(items[position])

        val bp = items[position] as BaseProduct

        Glide.with(viewHolder.itemView.context)
            .load(bp.photo)
            .into(viewHolder.itemView.iv_photo)

        if (viewHolder.itemView.btn_add == null) return
        viewHolder.itemView.btn_add.setOnClickListener {
            if (onItemClickListener != null){
                onItemClickListener?.onItemClick(items[position])
                bp.isAddToCart = !bp.isAddToCart
                viewHolder.itemView.btn_add.setText(if(bp.isAddToCart) "ADDED" else "ADD")
            }
        }
        viewHolder.itemView.btn_add.setText(if(bp.isAddToCart) "ADDED" else "ADD")

    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position, items[position])
    }

    internal interface Binder<T> {
        fun bind(data: T)
    }

    interface OnItemClickListener<L> {
        fun onItemClick(data: L)
    }
}