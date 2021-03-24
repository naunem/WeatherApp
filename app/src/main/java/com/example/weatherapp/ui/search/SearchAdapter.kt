package com.example.weatherapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weatherapp.data.source.model.Location
import com.example.weatherapp.databinding.ItemSearchResultBinding
import com.example.weatherapp.ui.base.BaseListAdapter

class SearchAdapter : BaseListAdapter<Location>(DiffItemCallBack()) {

    internal var onItemFavoriteClick: (location: Location) -> Unit = {}

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

        init {
            binding.imgFavorite.run {
                setOnClickListener {
                    isSelected = !isSelected
                    getItem(adapterPosition).apply {
                        isFavorite = isSelected
                        onItemFavoriteClick(this)
                    }
                }
            }
        }

        override fun onBind(position: Int) {
            binding.run {
                getItem(position).run {
                    tvTitle.text = title
                    tvCity.text = locationType
                    tvLatLong.text = latLong
                    imgFavorite.isSelected = isFavorite
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