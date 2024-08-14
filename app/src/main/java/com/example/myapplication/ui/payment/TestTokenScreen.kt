package com.example.myapplication.ui.payment

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.viewmodel.TokenViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.TokenResponse

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Test(viewModel: TokenViewModel = viewModel(factory = TokenViewModel.Factory)){

    val state = viewModel._tokenResponse.collectAsState()
    val response = state.value

    when(response){
        is TokenResponse.Success ->{
            Text(text="Success")
        }
        is TokenResponse.Failure -> {
            Text(text="Failure")
        }
        is TokenResponse.Loading -> {
            Text(text="Loading")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun TestPreview(){
    MyApplicationTheme {
        Test()
    }
}