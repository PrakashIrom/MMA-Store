package com.example.myapplication.api

import android.util.Base64
import com.example.myapplication.model.data.AccessTokenResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

private const val url = "https://api-m.sandbox.paypal.com"

class CreateTokenApi(val clientId: String, val secretId: String) {

    private fun getHttpClient(): OkHttpClient {
       // val clientId = userPreference.clientRepo.access_client_id
        //val secretId = userPreference.clientRepo.access_secret_id
        val credential = Base64.encodeToString("$clientId:$secretId".toByteArray(), Base64.NO_WRAP)
        //Base64 encoding is a method used to convert binary data (which is data in the form of bytes, typically
        // represented in base-2, or binary, format) into a text string that uses only ASCII characters. This is
        // useful because some communication channels and protocols are designed to handle text data more easily than binary data.
        //Even though clientId and secretId are strings, the reason you encode them again is to create a single,
        // encoded string in the format clientId:secretId that can be safely transmitted and recognized by
        // the server as a valid set of credentials.
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Basic $credential")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    interface ApiTokenService {
        @FormUrlEncoded
        @POST("/v1/oauth2/token")
        @Headers("Content-Type: application/x-www-form-urlencoded")
        suspend fun getAccessToken(
            @FieldMap grantType: HashMap<String, String> = hashMapOf("grant_type" to "client_credentials")
        ): AccessTokenResponse
    }

    val retrofitToken = Retrofit.Builder().baseUrl(url)
        .client(getHttpClient())
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    val retrofit: ApiTokenService by lazy { retrofitToken.create(ApiTokenService::class.java)

    }

}

