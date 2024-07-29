package com.example.myapplication.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun OrderScreen(title: MutableState<String>){
    title.value = "ORDERS"
}