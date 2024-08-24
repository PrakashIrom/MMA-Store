package com.example.myapplication.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

const val ORDER = "https://api-m.sandbox.paypal.com/v2/checkout/orders/"

class InitiateOrder(private val tokenId: String) {

    private fun okHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor {
                val newRequest = it.request().newBuilder()
                    .addHeader("Authorization","Bearer $tokenId")
                    .build()
                    it.proceed(newRequest)
            }.build()
    }

    interface InitiateOrderService {
        @POST("{orderID}/capture")
        @Headers("Content-Type: application/json")
        suspend fun captureOrder(@Path("orderId") orderId: String)

        @POST("{orderId}/authorize")
        @Headers("Content-Type: application/json")
        suspend fun authorizeOrder(@Path("orderId") orderId: String)
    }

    val retrofit:Retrofit = Retrofit.Builder()
        .baseUrl(ORDER)
        .client(okHttpClient())
        .addConverterFactory(Json.asConverterFactory(contentType = "application/json".toMediaType()))
        .build()

    val orderService: InitiateOrderService by lazy{
        retrofit.create(InitiateOrderService::class.java)
    }
}