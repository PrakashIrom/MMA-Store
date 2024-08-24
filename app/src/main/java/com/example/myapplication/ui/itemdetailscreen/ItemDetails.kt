package com.example.myapplication.ui.itemdetailscreen

import android.annotation.SuppressLint
import android.widget.LinearLayout
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.model.data.Apparel
import com.example.myapplication.ui.navigationdrawer.Screens
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.paypal.android.paymentbuttons.PayPalButton
import com.paypal.android.paymentbuttons.PaymentButtonShape
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetails(apparel: Apparel, navController: NavHostController, title: MutableState<String>,
                selected:MutableState<MutableMap<Apparel,String>>, ){

    title.value = apparel.name
    val sheetState = rememberModalBottomSheetState()
    val showBottomSheet = remember { mutableStateOf(false) }

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
            when(apparel.gender)
            {
                Screens.WOMEN.name -> { Text(text = stringResource(id = R.string.woman),
                    modifier = Modifier.padding(10.dp))
                }
                Screens.MEN.name -> { Text(text = stringResource(id = R.string.man)
                , modifier = Modifier.padding(10.dp)
                )}
                else -> {
                    Text(text = stringResource(id = R.string.kid),
                    modifier = Modifier.padding(10.dp))
                }
            }
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)  // Add padding if needed
            ) {
                OutlinedButton(
                    onClick = {
                        showBottomSheet.value = true
                     },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()  // Ensure Row takes the full width
                    ) {
                        Text(
                            text =  selected.value[apparel] ?: "Select Size",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)  // Allow text to take up remaining space
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Select Size",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            PayPalButtonView(onClick = {})
            SelectSizeBottomSheet(sheetState = sheetState, showBottomSheet = showBottomSheet, selected = selected, apparel)
         }
       }
    }
}

@Composable
fun PayPalButtonView(onClick: () -> Unit) {

    val context = LocalContext.current

    AndroidView(
        factory = { ctx ->
            PayPalButton(ctx).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                setOnClickListener {
                    onClick()
                }
                shape = PaymentButtonShape.PILL
            }
        },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .height(38.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectSizeBottomSheet(sheetState: SheetState, showBottomSheet: MutableState<Boolean>,selected:MutableState<MutableMap<Apparel,String>>, apparel: Apparel){

    val sizes = listOf("Small", "Medium", "Large")
    val scope = rememberCoroutineScope()

    if(showBottomSheet.value) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet.value = false }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                sizes.forEach { item_size ->
                    Text(
                        text = item_size,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                val newMap = selected.value.toMutableMap()
                                newMap[apparel] = item_size
                                selected.value = newMap
                                scope
                                    .launch {
                                        sheetState.hide()
                                    }
                                    .invokeOnCompletion {
                                        if (!sheetState.isVisible) {
                                            showBottomSheet.value = false
                                        }
                                    }
                            }
                            .padding(16.dp)
                    )
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
        ItemDetails(apparel,nav,title,mutableStateOf(mutableMapOf<Apparel,String>()))
    }
}