package com.example.mge_vk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mge_vk.ui.theme.MGEVKTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MGEVKTheme {

                }
            }
        }
    }


@Composable
fun Screen(){
    Column(Modifier.fillMaxHeight()
        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp, top = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween

    ) {
        Column {
            Title()
            Spacer(Modifier.height(16.dp))
            App(
                name = "Приложение",
                icon = Icons.Default.AccountBox,
                tag = "month of games",
                stars = 4.7
            )
            App(
                name = "MAx",
                icon = Icons.Default.AccountBox,
                tag = "month of games",
                stars = 3.6
            )
            App(
                name = "VKontakte",
                icon = Icons.Default.AccountBox,
                tag = "month of games",
                stars = 2.5
            )


        }
        Footer()
    }
}
@Composable
fun Footer(){

    Row(verticalAlignment = Alignment.Bottom,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(imageVector = Icons.Default.AccountCircle,
            modifier = Modifier.clickable(enabled = true, onClick = {})
                .size(50.dp),
            contentDescription = "lol"
        )
        Icon(imageVector = Icons.Default.Search,
            modifier = Modifier.clickable(enabled = true, onClick = {})
                .size(50.dp),
            contentDescription = "lol"
        )
        Icon(imageVector = Icons.Default.AddCircle,
            modifier = Modifier.clickable(enabled = true, onClick = {})
                .size(50.dp),
            contentDescription = "lol"
        )
        Icon(imageVector = Icons.Default.Favorite,
            modifier = Modifier.clickable(enabled = true, onClick = {})
                .size(50.dp),
            contentDescription = "lol"
        )

    }
}

@Preview(showBackground = true)
@Composable
fun Greeting(){
    MGEVKTheme {
        com.example.mge_vk.Screen()
    }
}