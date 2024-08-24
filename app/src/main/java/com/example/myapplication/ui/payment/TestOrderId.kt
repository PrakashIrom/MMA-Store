package com.example.myapplication.ui.payment

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.model.data.Apparel
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.OrderIDViewModel
import com.example.myapplication.viewmodel.OrderState
import com.paypal.android.paypalwebpayments.PayPalWebCheckoutFundingSource
import com.paypal.android.paypalwebpayments.PayPalWebCheckoutRequest

@Composable
fun TestScreen(viewModel: OrderIDViewModel = viewModel(factory = OrderIDViewModel.Factory)){

    val item = Apparel("","","5.00","","")
    viewModel.getOrderId(item)
    val state = viewModel.orderState.collectAsState()

    when(val response = state.value){
        is OrderState.Success -> {
            Text(text = response.orderResponse.id)
            val payPalWebCheckoutRequest = PayPalWebCheckoutRequest(response.orderResponse.id, fundingSource = PayPalWebCheckoutFundingSource.PAYPAL)
       // have to call the function for paypal request inside the viewModel class
        }
        is OrderState.Loading -> {
            Text(text = "Loading")
        }
        is OrderState.Error -> {
            Text(text = "Error")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestScreenPreview(){
    MyApplicationTheme {
        TestScreen()
    }
}