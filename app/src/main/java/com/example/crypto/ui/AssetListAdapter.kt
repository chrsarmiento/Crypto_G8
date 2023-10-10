package com.example.crypto.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.crypto.R
import com.example.crypto.data.local.AssetEntity
import com.example.crypto.databinding.AssetListItemBinding
import com.squareup.picasso.Picasso

class AssetListAdapter(var itemClick: OnItemClickListener) : ListAdapter<AssetEntity, AssetListAdapter.AssetEntityViewHolder>(AssetsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetEntityViewHolder {
        return AssetEntityViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AssetEntityViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        holder.itemView.setOnClickListener {
            itemClick.onItemClick(current)
        }
    }

    class AssetEntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = AssetListItemBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(assetEntity: AssetEntity) {

            binding.assetPosition.text = assetEntity.rank

            Picasso.get()
                .load("https://static.coincap.io/assets/icons/${assetEntity.symbol?.lowercase()}@2x.png")
                .into(binding.assetImage)

            binding.assetId.text = assetEntity.name

            binding.assetUsdPrice.text = "1 ${assetEntity.symbol} = ${assetEntity.priceUsd} USD"

        }

        companion object {
            fun create(parent: ViewGroup): AssetEntityViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.asset_list_item, parent, false)
                return AssetEntityViewHolder(view)
            }
        }
    }

    class AssetsComparator : DiffUtil.ItemCallback<AssetEntity>() {
        override fun areItemsTheSame(oldItem: AssetEntity, newItem: AssetEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: AssetEntity, newItem: AssetEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }
}