package com.example.myapplication.api

import com.example.myapplication.model.data.Apparel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

const val BASE_URL = "http://192.168.0.123:8080"

interface APIService {
    @GET("/all")
    suspend fun getAllApparels():List<Apparel>

    @GET("/men")
    suspend fun getMenApparels():List<Apparel>

    @GET("/women")
    suspend fun getWomenApparels():List<Apparel>
}

val retrofit: Retrofit = Retrofit
    .Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .build()

object Api{
    val retrofitService: APIService by lazy{
        retrofit.create(APIService::class.java)
    }
}