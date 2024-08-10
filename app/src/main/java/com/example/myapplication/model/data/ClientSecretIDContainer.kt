package com.example.myapplication.model.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

interface IDContainer{
    val clientRepo: ClientSecretIDRepo
}

class DataContainer(dataStore: DataStore<Preferences>): IDContainer{
    override val clientRepo: ClientSecretIDRepo by lazy{
        ClientSecretIDRepo(dataStore)
    }
}