package com.example.myapplication.api

import com.example.myapplication.model.data.OrderResponse
import com.example.myapplication.model.data.PayPalOrderRequest
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

const val ORDERS_URL = "https://api-m.sandbox.paypal.com/v2/checkout/"

class CreateOrderId(private val token: String) {


    private fun getHttpClient():OkHttpClient {

            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        return OkHttpClient.Builder()
            .addInterceptor{chain->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(logging)
            .build()
    }

    interface PaypalOrderService {
        @POST("orders")
        @Headers("Content-Type: application/json")
       suspend fun getOrderId(
            @Body orderRequest: PayPalOrderRequest
        ): OrderResponse
    }

    val orderIdRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(ORDERS_URL)
        .client(getHttpClient())
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    val retrofitService: PaypalOrderService by lazy {
            orderIdRetrofit.create(PaypalOrderService::class.java)
        }

}

