package com.example.myapplication.ui.payment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.payment.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.OrderIDViewModel
import com.example.myapplication.viewmodel.OrderState

class PaymentActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                TestOrderScreen()
            }
        }
    }
}

@Composable
fun TestOrderScreen(viewModel: OrderIDViewModel = viewModel(factory = OrderIDViewModel.Factory)){

    viewModel.authorizeOrder()
    val state = viewModel.orderState.collectAsState()

    when(state.value){
        is OrderState.Success -> {
        }
        is OrderState.Loading -> {
            Text(text = "Loading")
        }
        is OrderState.Error -> {
            Text(text = "Error")
        }
        is OrderState.SuccessOrder -> {
            Text("Successful")
        }
    }

}