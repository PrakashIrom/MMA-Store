package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.APIService
import com.example.myapplication.model.data.Apparel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface UIApparelState {
    object Loading : UIApparelState
    object Error : UIApparelState
    data class Success(val datas: List<Apparel>) : UIApparelState

}
sealed interface UIMenState{
    object Loading: UIMenState
    object Error: UIMenState
    data class Success(val datas: List<Apparel>) : UIMenState
}
sealed interface UIKidsState{
    object Loading: UIKidsState
    object Error: UIKidsState
    data class Success(val datas: List<Apparel>) : UIKidsState
}
sealed interface UIWomenState{
    object Loading: UIWomenState
    object Error: UIWomenState
    data class Success(val datas: List<Apparel>): UIWomenState
}

class ApparelViewModel(private val Api: APIService) : ViewModel() {

    private val _uiState = MutableStateFlow<UIApparelState>(UIApparelState.Loading)
    val uiState: StateFlow<UIApparelState> = _uiState
    private val _uiMenState = MutableStateFlow<UIMenState>(UIMenState.Loading)
    val uiMenState: StateFlow<UIMenState> = _uiMenState
    private val _uiWomenState = MutableStateFlow<UIWomenState>(UIWomenState.Loading)
    val uiWomenState: StateFlow<UIWomenState> = _uiWomenState
    private val _uiKidsState = MutableStateFlow<UIKidsState>(UIKidsState.Loading)
    val uiKidsState: StateFlow<UIKidsState> = _uiKidsState

    init {
        getApparels()
        getMenApparels()
        getWomenApparels()
        getKidsApparels()
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

    private fun getMenApparels(){
        viewModelScope.launch{
            _uiMenState.value = try{
                val response = Api.getMenApparels()
                UIMenState.Success(response)
            }
            catch(e:Exception){
                Log.e("Men Apparel Error",e.message.toString())
                UIMenState.Error
            }
        }
    }

    private fun getWomenApparels(){
        viewModelScope.launch{
            _uiWomenState.value = try{
                val response = Api.getWomenApparels()
                UIWomenState.Success(response)
            }
            catch(e:Exception){
                Log.e("Women Apparel Error", e.message.toString())
                UIWomenState.Error
            }
        }
    }

    private fun getKidsApparels(){
        viewModelScope.launch{
            _uiKidsState.value = try{
                val response = Api.getKidsApparels()
                UIKidsState.Success(response)
            }
            catch(e:Exception){
                Log.e("MeKids Apparel Error",e.message.toString())
                UIKidsState.Error
            }
        }
    }

}
