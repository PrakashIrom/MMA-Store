package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Column
import com.example.myapplication.ui.navigationdrawer.Screens


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ShopScreens(title: MutableState<String>){

    title.value = "SHOP"
    val navController = rememberNavController()
    Column {
        Row() {
            Text("Men", modifier = Modifier.padding(start = 18.dp))
            Text("Women", modifier = Modifier.padding(start = 15.dp))
            Text("Kids", modifier = Modifier.padding(start = 15.dp))
        }
        HorizontalDivider(color = Color.Gray,modifier=Modifier.padding(top=5.dp))
    }
    NavHost(navController = navController, startDestination = Screens.MEN.name){
        composable(Screens.MEN.name){

        }
        composable(Screens.MEN.name){

        }
        composable(Screens.WOMEN.name){

        }
        composable(Screens.KIDS.name){

        }
    }
}