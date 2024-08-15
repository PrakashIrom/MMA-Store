package com.example.myapplication.ui.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.navigationdrawer.Screens
import com.example.myapplication.ui.theme.DarkBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ShopTopBar(search: MutableState<String>, drawerState: DrawerState, title: MutableState<String>,
               navController: NavHostController,
               selectedCategory: MutableState<String>, scope: CoroutineScope
){
        Column {
        Box(modifier = Modifier.padding(5.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Hamburger Icon",
                    tint = Color.Black,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .size(30.dp)
                )
            }
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = title.value,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            SearchBarWithAnimation(search = search)
        }
        }
            Row() {
                Text("All",
                    color = if (selectedCategory.value == Screens.SHOP.name) DarkBlue else Color.Black,
                    fontWeight = if (selectedCategory.value == Screens.SHOP.name) FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier
                        .padding(start = 18.dp)
                        .clickable {
                            selectedCategory.value = Screens.SHOP.name
                            navController.navigate(Screens.SHOP.name)
                        }
                )
                Text("Men",
                    color = if (selectedCategory.value == Screens.MEN.name) DarkBlue else Color.Black,
                    fontWeight = if (selectedCategory.value == Screens.MEN.name) FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.padding(start = 15.dp)
                        .clickable {
                            selectedCategory.value = Screens.MEN.name
                            navController.navigate(Screens.MEN.name)
                        }
                )
                Text("Women",
                    color = if (selectedCategory.value == Screens.WOMEN.name) DarkBlue else Color.Black,
                    fontWeight = if (selectedCategory.value == Screens.WOMEN.name) FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.padding(start = 15.dp)
                        .clickable {
                            selectedCategory.value = Screens.WOMEN.name
                            navController.navigate(Screens.WOMEN.name)
                        }
                )
                Text("Kids",
                    color = if (selectedCategory.value == Screens.KIDS.name) DarkBlue else Color.Black,
                    fontWeight = if (selectedCategory.value == Screens.KIDS.name) FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.padding(start = 15.dp)
                        .clickable {
                            selectedCategory.value = Screens.KIDS.name
                            navController.navigate(Screens.KIDS.name)
                        }
                )
            }
            HorizontalDivider(color = DarkBlue, modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarWithAnimation(search: MutableState<String>) {

    var searchVisible by remember { mutableStateOf(false) }
    var iconVisible by remember { mutableStateOf(true) }

    if(iconVisible)
        IconButton(
        onClick = { searchVisible = !searchVisible
        iconVisible = false
        }
    ) {
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
    }
        AnimatedVisibility(
            visible = searchVisible,
            enter = fadeIn() + expandHorizontally(expandFrom = Alignment.End), // Slide in from the right
            exit = fadeOut() + shrinkHorizontally(shrinkTowards = Alignment.End), // Slide out to the right
            modifier = Modifier
                .widthIn(min = 300.dp, max = 400.dp)
                .height(58.dp)
        ) {
            OutlinedTextField(
                value = search.value,
                onValueChange = { search.value = it },
                label = { Text("Search") },
                leadingIcon = {   IconButton(
                    onClick = { searchVisible = !searchVisible
                    iconVisible = true
                    }
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }},
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color.Black, // Text color
                    cursorColor = Color.Black, // Cursor color
                    focusedBorderColor = Color.Gray, // Border color when focused
                    unfocusedBorderColor = Color.LightGray // Border color when unfocused
                ),
                shape = RoundedCornerShape(15.dp)
            )
        }
}


