package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.myapplication.model.data.DataContainer

private const val PREFERENCE_NAME = "ClientSecretID"
private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

class IDApplication: Application() {

    lateinit var userPreference: DataContainer

    override fun onCreate() {
        super.onCreate()

        userPreference = DataContainer(datastore)
    }
}