package com.example.crypto.utils

import android.util.Log
import com.example.crypto.CryptoApp
import com.example.crypto.data.remote.CoincapRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class UpdateData {
    companion object {

        // ESTE METODO SIRVE PARA OBTENER LOS DATOS ACTUALIZADOS CUANDO SE INSTALA LA APP

        suspend fun getUpdatedData() = coroutineScope {
            launch(Dispatchers.IO) {
                val service = CoincapRetrofitClient.retrofitInstance()
                val response = service.getData()

                val data = response.body()

                val app = CryptoApp()

                app.repository.deleteAssets()

                if (response.isSuccessful) {
                    if (data != null) {

                        for (asset in data.data) {
                            Log.i("Asset ", asset.id)
                            app.repository.insertAsset(asset)
                        }
                    }
                } else {
                    Log.e("UpdateData Error", "Ocurrió un error al ejecutar getUpdatedData")
                }
            }
        }
    }
}