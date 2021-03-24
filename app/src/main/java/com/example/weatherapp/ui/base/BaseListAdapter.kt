package com.example.weatherapp.ui.base

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<T>(diffCallback: DiffCallback<T>) :
    ListAdapter<T, BaseListAdapter.ItemViewHolder>(diffCallback) {

    open class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        open fun onBind(position: Int) = Unit
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(position)
    }

    abstract class DiffCallback<T> : DiffUtil.ItemCallback<T>() {
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }
}
