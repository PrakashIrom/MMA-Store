package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.myapplication.api.APIService
import com.example.myapplication.api.Api
import com.example.myapplication.model.data.DataContainer

private const val PREFERENCE_NAME = "ClientSecretID"
private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

class ShopApplication: Application() {

    lateinit var userPreference: DataContainer
    lateinit var API: APIService

    override fun onCreate() {
        super.onCreate()

        API = Api.retrofitService
        userPreference = DataContainer(datastore)

    }

}