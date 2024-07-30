package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.AsyncImage
import com.example.myapplication.model.data.Apparel

@Composable
fun AllPants(viewModel: ApparelViewModel = viewModel()){

    val state by viewModel.uiState.collectAsState()

    when(val response = state){
        is UIApparelState.Success -> {
            ShowItems(response.datas)
        }
        is UIApparelState.Error -> {

        }

        is UIApparelState.Loading -> {

        }
    }
}

@Composable
fun ShowItems(items: List<Apparel>){
    Column {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(items) { item ->
                Column(modifier = Modifier.padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally,
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
                    Text(text = item.name,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}