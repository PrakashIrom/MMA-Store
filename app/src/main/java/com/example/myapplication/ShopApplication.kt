package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.myapplication.dependencyinjection.apiModule
import com.example.myapplication.model.data.DataContainer
import org.koin.core.context.GlobalContext.startKoin

private const val PREFERENCE_NAME = "ClientSecretID"
private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

class ShopApplication: Application() {

    lateinit var userPreference: DataContainer

    override fun onCreate() {
        super.onCreate()

        startKoin{
            modules(apiModule)
        }
        userPreference = DataContainer(datastore)

    }
}