package com.example.mge_vk

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun HomeScreen(navController: NavController,
               onInstallApp: (Int) -> Unit,
               apps: List<AppData> = appsList,
               selectedCategory: String = "Все"
               )
{
    if (apps.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Загрузка приложений...", color = MaterialTheme.colorScheme.onSurface)
        }
        return
    }
    val filtered = apps.filter{ it.tag == selectedCategory}
    Column(Modifier
        .fillMaxHeight()
        .background(MaterialTheme.colorScheme.background)
        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp, top = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Title(selectedCategory)
        Row(
            modifier = Modifier.height(10.dp)
        ) {

        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (selectedCategory == "Все") {
                items(apps) { appData ->
                    App(
                        name = appData.appName,
                        icon = painterResource(appData.appIcon),
                        tag = appData.tag,
                        stars = appData.stars,
                        ageRating = appData.ageRating,
                        navController = navController,
                        appId = appData.appId,
                        onInstallClick = { onInstallApp(appData.appId) }
                    )
                }
            } else {
                items(filtered) { appData ->
                        App(
                            name = appData.appName,
                            icon = painterResource(appData.appIcon),
                            tag = appData.tag,
                            stars = appData.stars,
                            ageRating = appData.ageRating,
                            navController = navController,
                            appId = appData.appId,
                            onInstallClick = { onInstallApp(appData.appId) }
                        )

                }
            }
        }

        Footer(navController)
    }
}

@Composable
fun Footer(navController: NavController){

    Row(verticalAlignment = Alignment.Bottom,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable(enabled = true, onClick = {
                navController.navigate("CategoriesScreen")
            })
        ) {
            Icon(
                imageVector = Icons.Default.Category,
                modifier = Modifier.size(50.dp),
                contentDescription = "lol",
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Категории",
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
