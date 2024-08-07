package com.example.myapplication.ui.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.navigationdrawer.Screens
import com.example.myapplication.ui.theme.DarkBlue
import com.example.myapplication.ui.theme.LightBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
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
            horizontalArrangement = Arrangement.SpaceBetween
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
            TextField(
                value = search.value,
                onValueChange = { search.value = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Gray
                    )
                },
                placeholder = {
                    Text(
                        text = "Search",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.wrapContentSize()
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .padding(top = 5.dp)
                    .background(
                        MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .width(250.dp)
                    .height(50.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = LightBlue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(10.dp)
            )
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
