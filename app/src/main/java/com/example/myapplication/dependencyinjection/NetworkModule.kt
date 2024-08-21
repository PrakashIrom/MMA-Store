package com.example.myapplication.dependencyinjection

import com.example.myapplication.api.APIService
import com.example.myapplication.api.ApparelRepository
import com.example.myapplication.api.BASE_URL
import com.example.myapplication.viewmodel.ApparelViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val apiModule = module{
    single{
         Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    single{
        get<Retrofit>().create(APIService::class.java)
    //get<Retrofit>(): This function is used to retrieve the Retrofit
    // instance that was defined earlier in the module. The get function is generic and can retrieve any
    // type that has been registered in the Koin container.
    }

    single{ApparelRepository(get())}

    viewModel{ApparelViewModel(get())}
}