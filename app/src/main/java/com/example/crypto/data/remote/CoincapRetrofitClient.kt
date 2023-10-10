package com.example.crypto.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoincapRetrofitClient {
    companion object {
        private const val BASE_URL = "https://api.coincap.io/v2/"

        fun retrofitInstance(): CoincapRetrofitApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(CoincapRetrofitApi::class.java)
        }
    }
}