package com.example.mge_vk

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class AppData(

    val appId: Int,

    val appName: String,

    val devName: String,

    val appIcon: Int,

    val tag: String,

    val stars: Double,

    val ageRating: Int,

    val description: String

)

val appsList = listOf(
    AppData(
        appId = 1001,
        appName = "My Gaming Edge - МГЕ БУЗ",
        devName = "МГЕ БУЗ",
        appIcon = R.drawable.buz,
        tag = "Развлечения",
        stars = 5.0,
        ageRating = 18,
        description = "Чел, иди в Roblox играй."
    ),
    AppData(
        appId = 1002,
        appName = "MAX: общение, звонки, сервисы",
        devName = "Коммуникационная Платформа",
        appIcon = R.drawable.max,
        tag = "Мессенджеры",
        stars = 4.6,
        ageRating = 6,
        description = "Все в одном приложении: мессенджер, звонки, чат-боты, мини приложения и другое."
    ),
    AppData(
        appId = 1003,
        appName = "ВКонтакте: чаты, видео, музыка",
        devName = "VK",
        appIcon = R.drawable.vk,
        tag = "Соцсети",
        stars = 4.3,
        ageRating = 12,
        description = "ВКонтакте - это общение, мессенджер и звонки, знакомства и игры, видео и музыка."
    ),
    AppData(
        appId = 1004,
        appName = "Team Fortress 2",
        devName = "Valve Corporation",
        appIcon = R.drawable.nichosi,
        tag = "Развлечения",
        stars = 4.3,
        ageRating = 12,
        description = "Навайбкодил епт"
    ),
    AppData(
        appId = 1005,
        appName = "Counter-Strike: Source",
        devName = "Valve Corporation",
        appIcon = R.drawable.nichosi,
        tag = "Развлечения",
        stars = 4.3,
        ageRating = 12,
        description = "Навайбкодил епт"
    ),
    AppData(
        appId = 1006,
        appName = "баблквас",
        devName = "суперсел",
        appIcon = R.drawable.nichosi,
        tag = "Развлечения",
        stars = 4.3,
        ageRating = 12,
        description = "Навайбкодил епт"
    ),
    AppData(
        appId = 1007,
        appName = "ааааааааа",
        devName = "мге",
        appIcon = R.drawable.nichosi,
        tag = "Развлечения",
        stars = 4.3,
        ageRating = 12,
        description = "Навайбкодил епт"
    ),

)

fun getAppById(appId: Int): AppData? {
    return appsList.find { it.appId == appId }
}