package com.example.gallerykotlin.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {


    @GET("/photos")
    fun getData(
        @Query("client_id") clientID: String?,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<ArrayList<ResponseDataItem>>

}