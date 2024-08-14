package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.ShopApplication
import com.example.myapplication.api.APIService
import com.example.myapplication.model.data.Apparel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface UIMenState{
    object Loading: UIMenState
    object Error: UIMenState
    data class Success(val datas: List<Apparel>) : UIMenState
}

class MenApparelViewModel(private val Api: APIService): ViewModel() {
    private val _uiState = MutableStateFlow<UIMenState>(UIMenState.Loading)
    val uiState: StateFlow<UIMenState> = _uiState

    fun getMenApparels(){
        viewModelScope.launch{
            _uiState.value = try{
             val response = Api.getMenApparels()
             UIMenState.Success(response)
            }
            catch(e:Exception){
                Log.e("Men Apparel Error",e.message.toString())
                UIMenState.Error
            }
        }
    }
    init{
        getMenApparels()
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory{
            initializer{
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ShopApplication)
                MenApparelViewModel(application.API)
            }
        }
    }
}