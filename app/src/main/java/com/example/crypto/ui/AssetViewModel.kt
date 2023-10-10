package com.example.crypto.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crypto.data.local.AssetEntity
import com.example.crypto.data.local.AssetRepository
import com.example.crypto.utils.UpdateData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AssetViewModel(private val repository: AssetRepository) : ViewModel() {
    val allAssets: LiveData<List<AssetEntity>> by lazy {
        repository.allAssets.also {
            getUpdatdData()
        }
    }

    fun getUpdatdData() {
        runBlocking {
            UpdateData.getUpdatedData()
        }
    }
}

class AssetViewModelFactory(private val repository: AssetRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssetViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AssetViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}