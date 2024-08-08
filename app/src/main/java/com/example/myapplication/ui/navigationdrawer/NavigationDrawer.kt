package com.example.myapplication.ui.navigationdrawer

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.HomeScreen
import com.example.myapplication.ui.screens.OrderScreen
import com.example.myapplication.ui.screens.SettingsScreen
import com.example.myapplication.ui.theme.Pink40
import kotlinx.coroutines.launch
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.myapplication.R
import com.example.myapplication.model.data.Apparel
import com.example.myapplication.ui.itemdetailscreen.ItemDetails
import com.example.myapplication.ui.screens.BagScreen
import com.example.myapplication.ui.screens.KidsPants
import com.example.myapplication.ui.screens.MenPants
import com.example.myapplication.ui.screens.ShopScreens
import com.example.myapplication.ui.screens.WomenPants
import com.example.myapplication.ui.topbar.BagTopBar
import com.example.myapplication.ui.topbar.HomeTopBar
import com.example.myapplication.ui.topbar.ItemDetailsTopBar
import com.example.myapplication.ui.topbar.OrderTopBar
import com.example.myapplication.ui.topbar.SettingsTopBar
import com.example.myapplication.ui.topbar.ShopTopBar
import com.example.myapplication.ui.topbar.UsersTopBar
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationDrawer(modifier: Modifier=Modifier.fillMaxSize()){

    val selectedCategory = remember { mutableStateOf(Screens.SHOP.name) }
    val title = remember{mutableStateOf("Home")}
    val navController = rememberNavController()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue= DrawerValue.Closed)
    val search = remember{mutableStateOf("")}

    ModalNavigationDrawer(
        drawerContent = {
              ModalDrawerSheet(
                  modifier = Modifier.width(300.dp)
              ) {
                    Box(
                        modifier = Modifier
                            .background(Gray)
                            .fillMaxWidth()
                            .height(150.dp)
                    ){
                        Column {
                            Icon(
                                imageVector = Icons.Default.Face,
                                contentDescription = "Account",
                                modifier = Modifier
                                    .size(100.dp)
                                    .absolutePadding(left = 10.dp, top = 10.dp)
                            )
                            Text(text = "Apui",
                                modifier = Modifier
                                    .size(100.dp)
                                    .absolutePadding(left = 24.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp
                                )
                        }
                    }
                  HorizontalDivider(color = Transparent,modifier= Modifier.padding(top=5.dp, bottom=5.dp))
                  NavigationDrawerItem(label = { Text(text="Home", color = Pink40) },
                      selected = false,
                      onClick = {
                          coroutineScope.launch {
                              drawerState.close()
                          }
                          navController.navigate(Screens.HOME.name)
                      },
                      icon = {Icon(imageVector = Icons.Default.Home, contentDescription = "Home")}
                      )
                  NavigationDrawerItem(label = { Text(text="Shop", color = Pink40) },
                      selected = false,
                      onClick = {
                          coroutineScope.launch {
                              drawerState.close()
                          }
                          navController.navigate(Screens.SHOP.name)
                      },
                      icon = {Icon(imageVector = Icons.Default.Star, contentDescription = "Home")}
                  )
                  NavigationDrawerItem(label = { Text(text="Orders", color = Pink40) },
                      selected = false,
                      onClick = {
                          coroutineScope.launch {
                              drawerState.close()
                          }
                          navController.navigate(Screens.ORDER.name)
                      },
                      icon = {Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Cart")}
                  )
                  NavigationDrawerItem(label = { Text(text="Bag", color = Pink40) },
                      selected = false,
                      onClick = {
                          coroutineScope.launch {
                              drawerState.close()
                          }
                          navController.navigate(Screens.BAG.name)
                      },
                          icon = { Icon(painter = painterResource(id = R.drawable.bag),
                              contentDescription = "Bag",
                              modifier = Modifier.size(23.dp)
                              )}
                  )
                  NavigationDrawerItem(label = { Text(text="Settings", color = Pink40) },
                      selected = false,
                      onClick = {
                          coroutineScope.launch {
                              drawerState.close()
                          }
                          navController.navigate(Screens.SETTINGS.name)
                      },
                      icon = {Icon(imageVector = Icons.Default.Settings,
                          contentDescription = "Settings")}
                  )
              }
        },
        drawerState = drawerState,
        gesturesEnabled = true,//by swiping the navigation drawer will open

    ) {
        Scaffold(
                topBar = {
                    TopBar(drawerState = drawerState, title, currentDestination, search, navController, selectedCategory, scope = coroutineScope)
                }
            ){
            NavHost(navController = navController, startDestination = Screens.HOME.name, modifier = Modifier.padding(it)) {
                composable(Screens.HOME.name){
                        HomeScreen(title)
                }
                composable(Screens.ORDER.name){
                        OrderScreen(title)
                }
                composable(Screens.SETTINGS.name){
                        SettingsScreen(title)
                }
                composable(Screens.SHOP.name){
                        ShopScreens(title, search, navController)
                }
                composable(Screens.MEN.name){
                    MenPants(search=search, navController = navController)
                }
                composable(Screens.WOMEN.name){
                    WomenPants(search=search, navController = navController)
                }
                composable(Screens.KIDS.name){
                    KidsPants(search=search, navController)
                }
                composable(route = "${Screens.DETAILS.name}/{apparel}"
                    , arguments = listOf(navArgument("apparel"){type = NavType.StringType})
                ){ backStackEntry ->
                    val apparelJson = backStackEntry.arguments?.getString("apparel")
                    val apparel = Gson().fromJson(apparelJson, Apparel::class.java)
                    ItemDetails(apparel = apparel, navController = navController)
                }
                composable(Screens.BAG.name){
                    BagScreen(title)
                }
                    // have to add for user also
            }
        }
    }
}

@Composable
fun TopBar(drawerState: DrawerState, title: MutableState<String>, currentDestination: String?,
           search: MutableState<String>, navController: NavHostController,
           selectedCategory: MutableState<String>, scope: CoroutineScope){

    when {
        currentDestination == Screens.HOME.name -> {
            HomeTopBar(title=title, drawerState=drawerState)
        }
        currentDestination == Screens.ORDER.name -> {
            OrderTopBar(navController, title)
        }
        currentDestination == Screens.SHOP.name ||
                currentDestination == Screens.MEN.name ||
                currentDestination == Screens.WOMEN.name ||
                currentDestination == Screens.KIDS.name -> {
            ShopTopBar(search, drawerState, title, navController, selectedCategory, scope)
        }
        currentDestination == Screens.SETTINGS.name -> {
            SettingsTopBar(navController, title)
        }
        currentDestination == Screens.USER.name -> {
            UsersTopBar(navController, title)
        }
        currentDestination?.startsWith(Screens.DETAILS.name) == true -> {
            ItemDetailsTopBar(navController = navController)
        }
        currentDestination == Screens.BAG.name -> {
            BagTopBar(navController = navController, title)
        }
    }
}
