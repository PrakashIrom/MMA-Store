package com.example.myapplication.ui.screens

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
import com.example.myapplication.viewmodel.ApparelViewModel
import com.example.myapplication.viewmodel.UIApparelState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.myapplication.model.data.Apparel
import com.example.myapplication.ui.itemdetailscreen.ItemDetails
import com.example.myapplication.ui.navigationdrawer.Screens
import com.example.myapplication.ui.theme.Blue700
import com.example.myapplication.ui.theme.DarkGray
import com.example.myapplication.ui.theme.DarkGreen
import com.example.myapplication.ui.theme.Red600
import com.google.gson.Gson
import android.net.Uri
@Composable
fun AllPants(search: MutableState<String>,navController: NavHostController,
             viewModel: ApparelViewModel = viewModel(), clickState: MutableState<Boolean>){

    val state by viewModel.uiState.collectAsState()

    when(val response = state){

        is UIApparelState.Success -> {
            ShowItems(response.datas, search=search, navController, clickState)
        }
        is UIApparelState.Error -> {

        }

        is UIApparelState.Loading -> {

        }

    }
}

@Composable
fun ShowItems(items: List<Apparel>, search:MutableState<String>,
              navController: NavHostController, clickState: MutableState<Boolean>){

    val filtered = if(search.value.isNotEmpty()) items.filter { it.name.contains(search.value, ignoreCase = true) } else items

    Column {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(filtered) { item ->
                    Column(
                        modifier = Modifier
                            .padding(5.dp)
                            .clickable {
                                clickState.value = true
                                val apparelJson = Uri.encode(Gson().toJson(item))
                                navController.navigate("${Screens.DETAILS.name}/$apparelJson")
                            }
                        ,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        AsyncImage(
                            model = item.imgUri,
                            contentDescription = "MMA pant",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(180.dp)
                                .fillMaxWidth()
                                .background(
                                    shape = RoundedCornerShape(10.dp),
                                    color = Color.DarkGray
                                )
                        )
                        Text(
                            text = if (item.quantity == "SOLD") "${item.quantity} OUT" else "IN ${item.quantity}",
                            color = if (item.quantity == "SOLD") Red600 else DarkGreen,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Text(
                            text = item.name,
                            color = DarkGray,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Spacer(modifier=Modifier.padding(3.dp))
                        Text(
                            text = item.price,
                            color = Blue700,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
            }
        }
    }
}