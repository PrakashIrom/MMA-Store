package com.example.myapplication.ui.navigationdrawer

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.Blue700
import com.example.myapplication.ui.theme.Pink40
import com.example.myapplication.ui.theme.Purple40
import com.example.myapplication.ui.theme.Red600
import com.example.myapplication.ui.theme.Yellow600
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer(modifier: Modifier=Modifier.fillMaxSize()){

    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue= DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerContent = {
              ModalDrawerSheet(
                  modifier = Modifier.width(300.dp)
              ) {
                    Box(
                        modifier = Modifier
                            .background(Purple40)
                            .fillMaxWidth()
                            .height(150.dp)
                    ){
                        Column {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Account",
                                modifier = Modifier
                                    .size(100.dp)
                                    .absolutePadding(left = 10.dp, top = 10.dp)
                            )
                            Text(text = "User",
                                modifier = Modifier.size(100.dp)
                                .absolutePadding(left = 24.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp
                                )
                        }
                    }
                  Divider()
                  NavigationDrawerItem(label = { Text(text="Home", color = Pink40) },
                      selected = false,
                      onClick = {
                          coroutineScope.launch {
                              drawerState.close()
                          }
                          navController.navigate(Screens.HOME.name){
                              popUpTo(0)
                          }
                      },
                      icon = {Icon(imageVector = Icons.Default.Home, contentDescription = "Home")}
                      )
                  NavigationDrawerItem(label = { Text(text="Orders", color = Pink40) },
                      selected = false,
                      onClick = {
                          coroutineScope.launch {
                              drawerState.close()
                          }
                          navController.navigate(Screens.ORDER.name){
                              popUpTo(0)
                          }
                      },
                      icon = {Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Cart")}
                  )
                  NavigationDrawerItem(label = { Text(text="Settings", color = Pink40) },
                      selected = false,
                      onClick = {
                          coroutineScope.launch {
                              drawerState.close()
                          }
                          navController.navigate(Screens.SETTINGS.name){
                              popUpTo(0)
                          }
                      },
                      icon = {Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")}
                  )
              }
        },
        drawerState = drawerState,
        gesturesEnabled = true,//by swiping the navigation drawer will open

    ) {
            Scaffold(
                topBar = {
                    val scope = rememberCoroutineScope()
                    TopAppBar(
                        title = { Text(text="Shop") },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Blue700,
                            titleContentColor = White,
                            navigationIconContentColor = Yellow600,
                            actionIconContentColor = Red600
                        ),
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                            ){
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Hamburger Icon",
                                    )
                            }
                        }
                    )
                }
            ){

            }
    }
}