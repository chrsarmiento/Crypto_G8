package com.example.crypto.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface CoincapRetrofitApi {

    @GET("assets")
    suspend fun getData(): Response<Data>
}