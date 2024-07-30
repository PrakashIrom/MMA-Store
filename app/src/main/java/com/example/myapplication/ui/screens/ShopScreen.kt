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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ShopScreens(title: MutableState<String>){

    title.value = "SHOP"
    val navController = rememberNavController()
    var selectedCategory by remember { mutableStateOf(Screens.ALL.name) }

    Column {
        Row() {
            Text("All",
                color = if(selectedCategory == Screens.ALL.name) Color.Blue else Color.Black,
                fontWeight = if (selectedCategory == Screens.ALL.name) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier
                    .padding(start = 18.dp)
                    .clickable {
                        selectedCategory = Screens.ALL.name
                        navController.navigate(Screens.ALL.name)
                    }
                )
            Text("Men",
                color = if(selectedCategory == Screens.MEN.name) Color.Blue else Color.Black,
                fontWeight = if (selectedCategory == Screens.MEN.name) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier.padding(start = 15.dp)
                .clickable {
                    selectedCategory = Screens.MEN.name
                    navController.navigate(Screens.MEN.name) }
            )
            Text("Women",
                color = if(selectedCategory == Screens.WOMEN.name) Color.Blue else Color.Black,
                fontWeight = if (selectedCategory == Screens.WOMEN.name) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier.padding(start = 15.dp)
                .clickable{
                    selectedCategory = Screens.WOMEN.name
                    navController.navigate(Screens.WOMEN.name)}
            )
            Text("Kids",
                color = if(selectedCategory == Screens.KIDS.name) Color.Blue else Color.Black,
                fontWeight = if (selectedCategory == Screens.KIDS.name) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier.padding(start = 15.dp)
                .clickable{
                    selectedCategory = Screens.KIDS.name
                    navController.navigate(Screens.KIDS.name)}
            )
        }
        HorizontalDivider(color = Color.Gray,modifier=Modifier.padding(top=5.dp, bottom=5.dp))
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