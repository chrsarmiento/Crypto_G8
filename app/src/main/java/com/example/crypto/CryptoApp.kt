package com.example.crypto

import android.app.Application
import com.example.crypto.data.local.AssetDatabase
import com.example.crypto.data.local.AssetRepository

class CryptoApp : Application() {

    private val database by lazy {
        AssetDatabase.getDatabase(this)
    }

    val repository by lazy {
        AssetRepository(database.assetDao())
    }
}