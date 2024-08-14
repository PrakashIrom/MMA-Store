package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.ui.navigationdrawer.NavigationDrawer
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.viewmodel.ApparelViewModel
import com.example.myapplication.viewmodel.KidsApparelViewModel
import com.example.myapplication.viewmodel.MenApparelViewModel
import com.example.myapplication.viewmodel.WomenApparelViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                NavigationDrawer()
            }
        }
    }
}
