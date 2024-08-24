package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.ShopApplication
import com.example.myapplication.api.CreateTokenApi
import com.example.myapplication.model.data.AccessTokenResponse
import com.example.myapplication.model.data.DataContainer
import com.example.myapplication.model.data.ClientSecretId
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

sealed interface TokenResponse
{
    data class Success(val token: AccessTokenResponse): TokenResponse
    object Failure: TokenResponse
    object Loading: TokenResponse
}

class TokenViewModel(private val userPreferences: DataContainer): ViewModel(){

    private val tokenResponse = MutableStateFlow<TokenResponse>(TokenResponse.Loading)
    val _tokenResponse: StateFlow<TokenResponse> = tokenResponse

    private fun accessToken(){
        viewModelScope.launch{
            tokenResponse.value = try{
                val dataId = ClientSecretId()
                userPreferences.clientRepo.save_client_secret_id(dataId)
                val clientId = userPreferences.clientRepo.access_client_id.first()
                val secretId = userPreferences.clientRepo.access_secret_id.first()
                val response = CreateTokenApi(clientId, secretId).retrofit.getAccessToken()
                TokenResponse.Success(response)
            }
            catch(e:Exception){
                Log.e("Token Response",e.message.toString())
                TokenResponse.Failure
            }
        }
    }

    fun saveAccessToken(token: String){
        Log.d("Save Token", token)
        viewModelScope.launch{
            userPreferences.clientRepo.save_token_id(token)
        }
    }

    init{
        accessToken()
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory{
            initializer{
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ShopApplication)
                TokenViewModel(application.userPreference)
            }
        }
    }
}