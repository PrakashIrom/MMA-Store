package com.example.myapplication.api

import com.example.myapplication.model.data.Apparel
import retrofit2.http.GET

const val BASE_URL = "http://192.168.0.118:8080"

interface APIService {
    @GET("/all")
    suspend fun getAllApparels():List<Apparel>

    @GET("/men")
    suspend fun getMenApparels():List<Apparel>

    @GET("/women")
    suspend fun getWomenApparels():List<Apparel>

    @GET("/kids")
    suspend fun getKidsApparels():List<Apparel>
}
