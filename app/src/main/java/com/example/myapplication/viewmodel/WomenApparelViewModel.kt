package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.Api
import com.example.myapplication.model.data.Apparel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface UIWomenState{
    object Loading: UIWomenState
    object Error: UIWomenState
    data class Success(val datas: List<Apparel>): UIWomenState
}

class WomenApparelViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<UIWomenState>(UIWomenState.Loading)
    val uiState: StateFlow<UIWomenState> = _uiState

    fun getWomenApparels(){
        viewModelScope.launch{
            _uiState.value = try{
                val response = Api.retrofitService.getWomenApparels()
                UIWomenState.Success(response)
            }
            catch(e:Exception){
                Log.e("Women Apparel Error", e.message.toString())
                UIWomenState.Error
            }
        }
    }

    init{
        getWomenApparels()
    }

}