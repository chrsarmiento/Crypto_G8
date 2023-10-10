package com.example.crypto.ui

import com.example.crypto.data.local.AssetEntity

interface OnItemClickListener {
    fun onItemClick(asset: AssetEntity)
}