package com.example.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("api/quotes")
    fun getQuote(): Call<List<Data>>
}