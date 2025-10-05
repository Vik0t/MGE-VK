package com.example.mge_vk

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
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
               apps: List<AppData> = appsList
               )
{
    val topApps = apps
        .sortedByDescending {it.stars}
        .take(10)
        .shuffled()



    Column(Modifier
        .fillMaxHeight()
        .background(MaterialTheme.colorScheme.background)
        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp, top = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Title()
        Row(
            modifier = Modifier.height(10.dp)
        ) {

        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )   {
            items(topApps) { appData ->
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

        Footer()
    }
}

@Composable
fun Footer(){

    Row(verticalAlignment = Alignment.Bottom,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable(enabled = true, onClick = {})
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                modifier = Modifier.size(50.dp),
                contentDescription = "lol",
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Профиль",
                modifier = Modifier.padding(top = 4.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable(enabled = true, onClick = {})
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                modifier = Modifier.size(50.dp),
                contentDescription = "lol",
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Поиск",
                modifier = Modifier.padding(top = 4.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable(enabled = true, onClick = {})
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                modifier = Modifier.size(50.dp),
                contentDescription = "lol",
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Добавить",
                modifier = Modifier.padding(top = 4.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable(enabled = true, onClick = {})
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                modifier = Modifier.size(50.dp),
                contentDescription = "lol",
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Избранное",
                modifier = Modifier.padding(top = 4.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
