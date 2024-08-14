package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.ShopApplication
import com.example.myapplication.api.APIService
import com.example.myapplication.api.Api
import com.example.myapplication.model.data.Apparel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface UIApparelState {
    object Loading : UIApparelState
    object Error : UIApparelState
    data class Success(val datas: List<Apparel>) : UIApparelState
}

class ApparelViewModel(private val Api: APIService) : ViewModel() {

    private val _uiState = MutableStateFlow<UIApparelState>(UIApparelState.Loading)
    val uiState: StateFlow<UIApparelState> = _uiState

    init {
        getApparels()
    }

    private fun getApparels() {
        viewModelScope.launch {
            _uiState.value = try {
                val response = Api.getAllApparels()
                if (response.isNotEmpty()) {
                    UIApparelState.Success(response)
                } else {
                    UIApparelState.Error // Handle empty response case if needed
                }
            } catch (e: Exception) {
                Log.e("ApparelError", e.message.toString())
                UIApparelState.Error
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory{
            initializer{
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ShopApplication)
                ApparelViewModel(application.API)
            }
        }
    }

}
