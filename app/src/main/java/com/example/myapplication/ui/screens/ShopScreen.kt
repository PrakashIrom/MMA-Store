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
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.model.data.Apparel
import com.example.myapplication.ui.itemdetailscreen.ItemDetails
import com.example.myapplication.ui.theme.DarkBlue
import com.google.gson.Gson

@Composable
fun ShopScreens(title: MutableState<String>, search: MutableState<String>,
                clickState: MutableState<Boolean>, selectedCategory: MutableState<String>, navControllerItemDetails: NavHostController){

    title.value = "SHOP"
    //val navController = rememberNavController()

    if(!clickState.value){
    Column {
        Row() {
            Text("All",
                color = if (selectedCategory.value == Screens.ALL.name) DarkBlue else Color.Black,
                fontWeight = if (selectedCategory.value == Screens.ALL.name) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier
                    .padding(start = 18.dp)
                    .clickable {
                        selectedCategory.value = Screens.ALL.name
                        navControllerItemDetails.navigate(Screens.ALL.name)
                    }
            )
            Text("Men",
                color = if (selectedCategory.value == Screens.MEN.name) DarkBlue else Color.Black,
                fontWeight = if (selectedCategory.value == Screens.MEN.name) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier.padding(start = 15.dp)
                    .clickable {
                        selectedCategory.value = Screens.MEN.name
                        navControllerItemDetails.navigate(Screens.MEN.name)
                    }
            )
            Text("Women",
                color = if (selectedCategory.value == Screens.WOMEN.name) DarkBlue else Color.Black,
                fontWeight = if (selectedCategory.value == Screens.WOMEN.name) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier.padding(start = 15.dp)
                    .clickable {
                        navControllerItemDetails.navigate(selectedCategory.value)
                    }
            )
            Text("Kids",
                color = if (selectedCategory.value == Screens.KIDS.name) DarkBlue else Color.Black,
                fontWeight = if (selectedCategory.value == Screens.KIDS.name) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier.padding(start = 15.dp)
                    .clickable {
                        selectedCategory.value = Screens.KIDS.name
                        navControllerItemDetails.navigate(Screens.KIDS.name)
                    }
            )
        }
        HorizontalDivider(color = DarkBlue, modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
        NavHost(navController = navControllerItemDetails, startDestination = Screens.ALL.name){
            composable(Screens.ALL.name){
                AllPants(search=search, navControllerItemDetails, clickState=clickState)
            }
            composable(Screens.MEN.name){
                MenPants(search=search, navController = navControllerItemDetails, clickState=clickState)
            }
            composable(Screens.WOMEN.name){
                WomenPants(search=search, navController = navControllerItemDetails, clickState=clickState)
            }
            composable(Screens.KIDS.name){
                KidsPants(search=search, navControllerItemDetails, clickState=clickState)
            }
            composable(route = "${Screens.DETAILS.name}/{apparel}"
                , arguments = listOf(navArgument("apparel"){type = NavType.StringType})
            ){ backStackEntry ->
                val apparelJson = backStackEntry.arguments?.getString("apparel")
                val apparel = Gson().fromJson(apparelJson, Apparel::class.java)
                ItemDetails(apparel = apparel, navController = navControllerItemDetails)
            }
        }
    }
    }
}