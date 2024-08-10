package com.example.myapplication.api

import android.util.Base64
import com.example.myapplication.model.data.AccessTokenResponse
import com.example.myapplication.model.data.DataContainer
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

private const val url = "https://api-m.sandbox.paypal.com"

class CreateApi(val userPreference: DataContainer) {


    private fun getHttpClient(): OkHttpClient {
        val clientId = userPreference.clientRepo.access_client_id
        val secretId = userPreference.clientRepo.access_secret_id
        val credential = Base64.encodeToString("$clientId:$secretId".toByteArray(), Base64.NO_WRAP)
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Basic $credential")
                    .build()
                chain.proceed(request)
            }
            .build()
    }


    interface ApiService {
        @POST("/v1/oauth2/token")
        @Headers("Content-Type: application/x-www-form-urlencoded")
        suspend fun getAccessToken(
            @Body grantType: HashMap<String, String> = hashMapOf("grant_type" to "client_credentials")
        ): AccessTokenResponse
    }

    val retrofitPaypal = Retrofit.Builder().baseUrl(url)
        .client(getHttpClient())
        .build()

}

