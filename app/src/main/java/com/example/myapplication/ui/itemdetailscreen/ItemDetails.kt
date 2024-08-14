package com.example.myapplication.ui.itemdetailscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.model.data.Apparel
import com.example.myapplication.ui.navigationdrawer.Screens
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun ItemDetails(apparel: Apparel, navController: NavHostController, title: MutableState<String>){
    title.value = apparel.name

    LazyColumn(){ item {
        Column( horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = apparel.imgUri,
                contentDescription = apparel.name,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .aspectRatio(1f)
                    .padding(top = 10.dp, bottom = 10.dp)
            )
            Text(
                text = title.value,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            )
            Text(
                text = apparel.price,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            )
            when(apparel.gender){
                Screens.WOMEN.name -> { Text(text = stringResource(id = R.string.woman)
                , modifier = Modifier.padding(10.dp),
                )}
                Screens.MEN.name -> { Text(text = stringResource(id = R.string.man)
                , modifier = Modifier.padding(10.dp)
                )}
                else -> { Text(text = stringResource(id = R.string.kid),
                    modifier = Modifier.padding(10.dp)
                    )}
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)  // Add padding if needed
            ) {
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .align(Alignment.Center)  // Center the button within the Box
                        .fillMaxWidth()  // Button takes the full width
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()  // Ensure Row takes the full width
                    ) {
                        Text(
                            text = "Select Size",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)  // Allow text to take up remaining space
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Select Size",
                            modifier = Modifier.size(24.dp)  // Adjust size as needed
                        )
                    }
                }
            }
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            {
             Text(text="Buy Now")
            }
        }
       }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewItemDetails(){
    MyApplicationTheme {
        val apparel = Apparel("men", "shirt", "100", "male", "2")
        val nav = rememberNavController()
        val title = mutableStateOf("")
        ItemDetails(apparel,nav,title)
    }
}