package com.example.weatherapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weatherapp.data.source.model.Location
import com.example.weatherapp.databinding.ItemSearchResultBinding
import com.example.weatherapp.ui.base.BaseListAdapter

class SearchAdapter : BaseListAdapter<Location>(DiffItemCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ViewHolder(
            ItemSearchResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ViewHolder(private val binding: ItemSearchResultBinding) :
        ItemViewHolder(binding.root) {
        override fun onBind(position: Int) {
            itemView.run {
                getItem(position).run {
                    binding.tvTitle.text = title
                    binding.tvCity.text = locationType
                    binding.tvLatLong.text = latLong
                }
            }
        }
    }

    private class DiffItemCallBack : BaseListAdapter.DiffCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.woeid == newItem.woeid
        }
    }
}