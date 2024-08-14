package com.example.myapplication.model.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ClientSecretIDRepo(private val datastore: DataStore<Preferences>) {

    companion object{
        val CLIENT_ID = stringPreferencesKey("client_id")
        val SECRET_ID = stringPreferencesKey("secret_id")
        val ORDER_ID = stringPreferencesKey("order_id")
    }

    val access_client_id: Flow<String> = datastore.data.map{
        preferences->
        preferences[CLIENT_ID] ?: "Null"
    }

    val access_secret_id: Flow<String> = datastore.data.map{
        preferences ->
        preferences[SECRET_ID] ?: "Null"
    }

    val accessOrderId: Flow<String> = datastore.data.map{
        preferences ->
        preferences[ORDER_ID] ?: "Null"
    }

    suspend fun save_client_secret_id(id: ClientSecretId){
        datastore.edit{
            preferences->
            preferences[CLIENT_ID] = id.CLIENT_ID
            preferences[SECRET_ID] = id.SECRET_ID
        }
    }

    suspend fun saveOrderId(id:OrderID){
        datastore.edit{
            preferences->
            preferences[ORDER_ID] = id.id
        }
    }

}