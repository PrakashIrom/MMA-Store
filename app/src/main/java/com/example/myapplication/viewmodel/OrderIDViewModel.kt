package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.ShopApplication
import com.example.myapplication.api.CreateOrderId
import com.example.myapplication.api.InitiateOrder
import com.example.myapplication.model.data.Amount
import com.example.myapplication.model.data.Apparel
import com.example.myapplication.model.data.DataContainer
import com.example.myapplication.model.data.OrderResponse
import com.example.myapplication.model.data.PayPalOrderRequest
import com.example.myapplication.model.data.PurchaseUnit
import com.example.paypal.PaypalService
import com.paypal.android.paypalwebpayments.PayPalWebCheckoutRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

sealed interface OrderState{
    data class Success(val orderResponse: OrderResponse): OrderState
    object Loading: OrderState
    object Error: OrderState

    object SuccessOrder: OrderState

}
class OrderIDViewModel(private val userPreferences: DataContainer): ViewModel() {

    private val _orderState = MutableStateFlow<OrderState>(OrderState.Loading)
    val orderState: StateFlow<OrderState> = _orderState

    fun getOrderId(task:Apparel){

        val amount = Amount("USD", "5.00")
        val purchaseUnits = listOf(PurchaseUnit(amount=amount))
        val orderRequest = PayPalOrderRequest("CAPTURE",purchaseUnits)

        viewModelScope.launch {
            _orderState.value = try{
                val token = userPreferences.clientRepo.access_token_id.first()
                val response = CreateOrderId(token).retrofitService.getOrderId(orderRequest)
                OrderState.Success(response)
            }
            catch(e: Exception){
                Log.e("Order Error", e.toString(),e)
                OrderState.Error
            }
        }
    }

    fun saveOrderId(orderId: String){
        viewModelScope.launch {
            userPreferences.clientRepo.saveOrderId(orderId)
        }
    }

    fun webCheckoutRequest(checkoutRequest: PayPalWebCheckoutRequest){
         val paypalService = PaypalService()
        paypalService.payPalWebCheckoutTapped(checkoutRequest)
    }

    fun authorizeOrder(){
        _orderState.value = OrderState.Loading
        viewModelScope.launch {
            _orderState.value = try {
                val token = userPreferences.clientRepo.access_token_id.first()
                val orderId = userPreferences.clientRepo.accessOrderId.first()
                InitiateOrder(token).orderService.authorizeOrder(orderId)
                OrderState.SuccessOrder
            } catch (e: Exception) {
                Log.e("Order Initiation Error", e.toString(), e)
                OrderState.Error
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory{
            initializer{
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ShopApplication)
                OrderIDViewModel(application.userPreference)
            }
        }
    }

}