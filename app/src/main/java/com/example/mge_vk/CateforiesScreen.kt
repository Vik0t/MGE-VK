package com.example.mge_vk


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController



@Composable
fun CategoryScreen(navController: NavController, apps: List<AppData> = emptyList()) {

    val uniqueTags = apps.map { it.tag }.distinct() + "Все"

    Column(
        modifier = Modifier.fillMaxSize().padding(bottom = 16.dp, top = 16.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Категории", fontSize = 30.sp)

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            modifier = Modifier.padding(top = 10.dp)

        ) {
            items(uniqueTags) { appData ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable(enabled = true, onClick = {
                        navController.navigate("HomeScreen/${appData}")
                    })
                ) {
                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = "",
                        modifier = Modifier.size(80.dp)
                    )
                    val countApps = apps.count { it.tag == appData }
                    Text(
                        text = appData,
                        fontSize = 20.sp
                    )
                    if (countApps != 0) {
                        Text("$countApps")
                    }
                }

            }
        }
        Footer(navController)
    }
}