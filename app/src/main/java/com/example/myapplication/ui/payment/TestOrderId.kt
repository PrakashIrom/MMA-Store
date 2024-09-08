package com.example.myapplication.ui.payment

import android.content.Intent
import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.model.data.Apparel
import com.example.myapplication.viewmodel.OrderIDViewModel
import com.example.myapplication.viewmodel.OrderState
import com.example.paypal.PaypalService

@Composable
fun TestOrderId(viewModel: OrderIDViewModel = viewModel(factory = OrderIDViewModel.Factory)){

    val item = Apparel("","","5.00","","")
    viewModel.getOrderId(item)
    val state = viewModel.orderState.collectAsState()
    val context = LocalContext.current

    when(val response = state.value){
        is OrderState.Success -> {
            viewModel.saveOrderId(response.orderResponse.id)
            Log.d("OrderId",response.orderResponse.id)
            val intent = Intent(context,PaypalService::class.java).apply{
                putExtra("orderId",response.orderResponse.id)
            }
            context.startActivity(intent)
        }
        is OrderState.Loading -> {
            Text(text = "Loading")
        }
        is OrderState.Error -> {
            Text(text = "Error")
        }
        is OrderState.SuccessOrder -> {
            Text(text = "Success Payment process")
        }
    }

}

