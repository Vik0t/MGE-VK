package com.example.mge_vk

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

@Preview(showBackground = true)
@Composable
fun PreviewScreen(){
    val navController2 = rememberNavController()
    HomeScreen(navController = navController2)
}

@Composable
fun HomeScreen(navController: NavController)
{
    val appsList = listOf(
        AppData("Приложение", R.drawable.nichosi, "month of games", 4.7),
        AppData("MAx", R.drawable.max, "month of games", 3.6),
        AppData("VKontakte", R.drawable.nichosi, "month of games", 2.5),
        AppData("MAx", R.drawable.max, "month of games", 3.6),
        AppData("Геи против Пидоров", R.drawable.max, "month of games", 3.6),
        AppData("MAx", R.drawable.max, "month of games", 3.6),
        AppData("MAx", R.drawable.max, "month of games", 3.6),
        AppData("MAx", R.drawable.max, "month of games", 3.6),
        AppData("MAx", R.drawable.max, "month of games", 3.6),
        AppData("MAx", R.drawable.max, "month of games", 3.6),
        AppData("MAx", R.drawable.max, "month of games", 3.6),
        AppData("MAx", R.drawable.max, "month of games", 3.6),
        AppData("MAx", R.drawable.max, "month of games", 3.6)
    )



    Column(Modifier
        .fillMaxHeight()
        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp, top = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Title()

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )   {
            items(appsList) { appData ->
                App(
                    name = appData.name,
                    icon = painterResource(appData.iconRes),
                    tag = appData.tag,
                    stars = appData.stars
                )
            }
        }

        Footer()
    }
}

data class AppData(
    val name: String,
    val iconRes: Int,
    val tag: String,
    val stars: Double
)

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
