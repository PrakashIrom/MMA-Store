package com.example.myapplication.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(title: MutableState<String>){

    val images = listOf(
        painterResource(R.drawable.conor),
        painterResource(R.drawable.jones),
        painterResource(R.drawable.tankdavis)
    )

    val pager = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = {images.size}
    )

    LaunchedEffect(pager){
        while(true){
            delay(3000)
            val nextPage = (pager.currentPage+1)
            if(nextPage<images.size){
                pager.animateScrollToPage(nextPage)
            }
            else{
                pager.scrollToPage(0)
            }

        }
    }

    HorizontalPager(state = pager) {
        page->
        Box(modifier=Modifier.fillMaxWidth().height(300.dp)) {
            Image(images[page],
                contentDescription = "Banner",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Row(modifier = Modifier.align(Alignment.BottomCenter)){
                repeat(images.size){ index->
                    val isSelected = (pager.currentPage % images.size) == index
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(if (isSelected) 12.dp else 8.dp) // Highlight the current dot
                            .clip(CircleShape)
                            .background(if (isSelected) Color.Green else Color.Red)
                    )
                }
            }
        }
    }

}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground=true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(title = mutableStateOf(""))
}
