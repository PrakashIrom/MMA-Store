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

sealed interface UIKidsState{
    object Loading: UIKidsState
    object Error: UIKidsState
    data class Success(val datas: List<Apparel>) : UIKidsState
}

class KidsApparelViewModel(private val Api: APIService): ViewModel() {
    val _uiState = MutableStateFlow<UIKidsState>(UIKidsState.Loading)
    var uiState: StateFlow<UIKidsState> = _uiState

    private fun getKidsApparels(){
        viewModelScope.launch{
            _uiState.value = try{
                val response = Api.getKidsApparels()
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

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory{
            initializer{
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ShopApplication)
                KidsApparelViewModel(application.API)
            }
        }
    }
}