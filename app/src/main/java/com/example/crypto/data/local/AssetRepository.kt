package com.example.crypto.data.local

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class AssetRepository(private val assetDao: AssetDao) {
    val allAssets: LiveData<List<AssetEntity>> = assetDao.getRankedAssets()

    @WorkerThread
    fun insertAsset(asset: AssetEntity) {
        assetDao.insertAsset(asset)
    }

    @WorkerThread
    fun updateAsset(asset: AssetEntity) {
        assetDao.updateAsset(asset)
    }

    @WorkerThread
    fun deleteAssets() {
        assetDao.deleteAssets()
    }

}