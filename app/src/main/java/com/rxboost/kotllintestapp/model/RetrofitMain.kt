package com.rxboost.kotllintestapp.model

import com.rxboost.kotllintestapp.model.data.StoresCallResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitMain {
    private val BASE_URL = "https://lcboapi.com"
    private val lcboapi: ApiInterface

    init {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(httpClient)
                .build()

        lcboapi = retrofit.create(ApiInterface::class.java)
    }

    fun getStores(): Call<StoresCallResult> {
        return lcboapi.getStores()
    }
}