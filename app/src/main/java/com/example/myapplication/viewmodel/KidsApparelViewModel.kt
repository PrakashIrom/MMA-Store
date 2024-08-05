package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.Api
import com.example.myapplication.model.data.Apparel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface UIKidsState{
    object Loading: UIKidsState
    object Error: UIKidsState
    data class Success(val datas: List<Apparel>) : UIKidsState
}

class KidsApparelViewModel: ViewModel() {
    val _uiState = MutableStateFlow<UIKidsState>(UIKidsState.Loading)
    var uiState: StateFlow<UIKidsState> = _uiState

    private fun getKidsApparels(){
        viewModelScope.launch{
            _uiState.value = try{
                val response = Api.retrofitService.getKidsApparels()
                UIKidsState.Success(response)
            }
            catch(e:Exception){
                Log.e("MeKids Apparel Error",e.message.toString())
                UIKidsState.Error
            }
        }
    }
    init{
        getKidsApparels()
    }
}