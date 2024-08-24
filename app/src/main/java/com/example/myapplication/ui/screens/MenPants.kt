package com.example.myapplication.ui.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.viewmodel.UIMenState
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.myapplication.model.data.Apparel
import com.example.myapplication.ui.navigationdrawer.Screens
import com.example.myapplication.ui.theme.Blue700
import com.example.myapplication.ui.theme.DarkGreen
import com.example.myapplication.ui.theme.Red600
import com.example.myapplication.viewmodel.ApparelViewModel
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel

@Composable
fun MenPants(
    menViewModel: ApparelViewModel = koinViewModel(), search: MutableState<String>,
    navController: NavHostController,
){

    val state by menViewModel.uiMenState.collectAsState()

    when(val response = state){
        is UIMenState.Success -> {
            ShowMenPants(response.datas, search, navController)
        }
        is UIMenState.Error -> {

        }
        is UIMenState.Loading -> {

        }
    }
}

@Composable
fun ShowMenPants(items: List<Apparel>, search: MutableState<String>, navController: NavHostController){

    val filtered = if(search.value.isNotEmpty()) items.filter{it.name.contains(search.value, ignoreCase = true)} else items
    // filtered is to show the search results in the search bar or else the whole items list
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(filtered) { item ->
                Column(modifier = Modifier
                    .padding(5.dp)
                    .clickable {
                        val apparelJson = Uri.encode(Gson().toJson(item))
                        navController.navigate("${Screens.DETAILS.name}/$apparelJson")
                    }
                    , horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AsyncImage(model = item.imgUri,
                        contentDescription = "MMA pant",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(180.dp)
                            .fillMaxWidth()
                            .background(shape = RoundedCornerShape(10.dp), color = Color.DarkGray)
                    )
                    Text(text =  if(item.quantity=="SOLD") "${item.quantity} OUT" else "IN ${item.quantity}",
                        color = if(item.quantity=="SOLD") Red600 else DarkGreen,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Text(text = item.name,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Spacer(modifier=Modifier.padding(3.dp))
                    Text(
                        text = "$${item.price}",
                        color = Blue700,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
}

