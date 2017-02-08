package com.rxboost.kotllintestapp.model

import com.rxboost.kotllintestapp.model.data.StoresCallResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {
    @Headers("Authorization:  Token token=\"MDplYWMxODc4Ni1lZDI0LTExZTYtODRkNy1iM2M4MGYzODViZTU6bndBczRVRFo3VGh0bmhaUWpBTGt4WmVtNDlRQW1uSmFtbnUz\"")
    @GET("/stores")
    fun getStores(): Call<StoresCallResult>
}