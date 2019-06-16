package com.theappbangla.mmecommerce.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Written by Shariful Islam Mubin [CSE 2K14, KUET]
 * Generic RecyclerView Adapter in Kotlin
 */

abstract class KtGenericAdapter<M> : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    var items: ArrayList<M>

    // abstract methods
    abstract fun getViewHolder(view: View, viewType: Int):RecyclerView.ViewHolder
    protected abstract fun getLayoutId(position: Int, obj: M): Int

    constructor(items: ArrayList<M>) {
        this.items = items
    }

    constructor() {
        items = arrayListOf()
    }

    fun setData(items: ArrayList<M>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun clearData() {
        items = arrayListOf()
        notifyDataSetChanged()
    }

    fun addData(item: M) {
        items.add(item)
        notifyItemInserted(items.count() - 1)
    }

    fun removeDataAt(position: Int) {
        items.removeAt(position)
        notifyItemRangeChanged(0, position)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): RecyclerView.ViewHolder {
        return getViewHolder(LayoutInflater.from(viewGroup.context).inflate(type, viewGroup, false), type);
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as Binder<M>).bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position, items[position])
    }

    internal interface Binder<T> {
        fun bind(data: T)
    }
}