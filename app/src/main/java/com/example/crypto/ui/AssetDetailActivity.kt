package com.example.crypto.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crypto.data.local.AssetEntity
import com.example.crypto.databinding.ActivityAssetDetailBinding
import com.squareup.picasso.Picasso

class AssetDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityAssetDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssetDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val asset = intent.extras?.getSerializable("asset") as AssetEntity

        Picasso.get()
            .load("https://static.coincap.io/assets/icons/${asset.symbol?.lowercase()}@2x.png")
            .resize(250, 250)
            .into(binding.assetImage)

        binding.assetName.text = asset.name
        binding.assetSymbol.text = asset.symbol
        binding.assetPrice.text = "1 ${asset.symbol} = ${asset.priceUsd} USD"

        if (asset.maxSupply == null) {
            binding.assetSupply.text = "Suply: Unavailable"
        } else {
            binding.assetSupply.text = "Suply: ${asset.supply} USD"
        }

        binding.assetMarketcap.text = "Market Cap: ${asset.marketCapUsd} USD"
    }
}