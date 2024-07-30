package com.example.myapplication.ui.screens

import androidx.compose.foundation.clickable
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
import com.example.myapplication.viewmodel.ApparelViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ShopScreens(title: MutableState<String>, viewModel: ApparelViewModel = viewModel()){


    title.value = "SHOP"
    val navController = rememberNavController()

    Column {
        Row() {
            Text("All",
                modifier = Modifier
                    .padding(start = 18.dp)
                    .clickable {
                        navController.navigate(Screens.ALL.name)
                    }
                )
            Text("Men", modifier = Modifier.padding(start = 15.dp)
                .clickable { navController.navigate(Screens.MEN.name) }
            )
            Text("Women", modifier = Modifier.padding(start = 15.dp)
                .clickable{ navController.navigate(Screens.WOMEN.name)}
            )
            Text("Kids", modifier = Modifier.padding(start = 15.dp)
                .clickable{ navController.navigate(Screens.KIDS.name)}
            )
        }
        HorizontalDivider(color = Color.Gray,modifier=Modifier.padding(top=5.dp))
        NavHost(navController = navController, startDestination = Screens.ALL.name){
            composable(Screens.ALL.name){
                AllPants()
            }
            composable(Screens.MEN.name){
                MenPants()
            }
            composable(Screens.WOMEN.name){
                WomenPants()
            }
            composable(Screens.KIDS.name){
                KidsPants()
            }
        }
    }
}