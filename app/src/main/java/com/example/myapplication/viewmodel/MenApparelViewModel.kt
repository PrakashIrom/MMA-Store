package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.Api
import com.example.myapplication.model.data.Apparel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface UIMenState{
    object Loading: UIMenState
    object Error: UIMenState
    data class Success(val datas: List<Apparel>) : UIMenState
}

class MenApparelViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<UIMenState>(UIMenState.Loading)
    val uiState: StateFlow<UIMenState> = _uiState

    fun getMenApparels(){
        viewModelScope.launch{
            _uiState.value = try{
             val response = Api.retrofitService.getMenApparels()
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
}