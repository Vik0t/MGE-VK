package com.example.mge_vk


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

val imageList = listOf(
    R.drawable.max1,
    R.drawable.max2,
    R.drawable.max3,
    R.drawable.max4,
    R.drawable.max5,
    R.drawable.max6
)
val appName = "max maxmaxmaxmaxmaxmaxmaxmaxmaxmaxmaxmaxmax"
val devName = "Vkontakte"
val rate = 18
val iconImg = R.drawable.nichosi
val description = "Скачай макс че как лох"


//@Preview(showBackground = true)
//@Composable
//fun GetCardPreview(){
//    val navController2 = rememberNavController()
//    Card(imageList = imageList,
//        appName = appName,
//        devName = devName,
//        rate = rate,
//        iconImg = iconImg,
//        description = description,
//        navController = navController2,
//        onInstallClick =
//    )
//}


@Composable
fun CardScreen(navController: NavController, appId: Int? = null,
               onInstallApp: (Int) -> Unit,
               apps: List<AppData> = appsList){

    val appData = if (appId != null) {
        getAppById(appId) ?: apps.first()
    } else {
        apps.first()
    }

    Card(imageList = imageList,
        appName = appData.appName,
        devName = appData.devName,
        rate = appData.ageRating,
        iconImg = appData.appIcon,
        description = appData.description,
        navController = navController,
        onInstallClick = { onInstallApp(appData.appId) }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Card(
    imageList: List<Int>,
    appName: String,
    devName: String,
    rate: Int,
    iconImg: Int,
    description: String,
    navController: NavController,
    onInstallClick: () -> Unit


    ){
    // Переменная для текста нового комментария
    var commentText by remember { mutableStateOf("") }


    // Список комментариев (изначально пустой)
    val comments = remember { mutableStateListOf<String>() }
    // Переменные для удаления комментария
    var showDeleteDialog by remember { mutableStateOf(false) }
    var commentToDelete by remember { mutableStateOf(-1) }

    // Список различных картинок для скролла


    // Основной контейнер со скроллом
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 100.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {

        // Верхняя часть
        Row {
            Button(
                modifier = Modifier.offset(x = 10.dp, y = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surface, // ИЗМЕНЕНО: использование темы
                    contentColor = MaterialTheme.colorScheme.onSurface // ИЗМЕНЕНО: использование темы
                ),
                onClick = { navController.navigateUp()}
            ) { Text("◀", color = MaterialTheme.colorScheme.onSurface) }
        }

        Row {
            Image(
                painter = painterResource(id = iconImg),//аватарка приложения
                contentDescription = "иконка",
                modifier = Modifier
                    .size(100.dp)
                    .offset(x = 25.dp, y = 30.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .offset(x = 50.dp, y = 35.dp)
                    .padding(end = 16.dp)
            ) {
                Text(
                    text=appName,//название приложения
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialTheme.colorScheme.onSurface, // ИЗМЕНЕНО: использование темы
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                Text(text=devName, Modifier.offset(y = 3.dp), color = MaterialTheme.colorScheme.onSurfaceVariant)
                //имя разработчика
                Text(text=rate.toString()+"+", Modifier.offset(y = 3.dp), color = MaterialTheme.colorScheme.onSurfaceVariant)
                //возрастной рейтинг
            }
        }

        Spacer(Modifier.size(32.dp))

        Button(
            onClick = onInstallClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .offset(y = 25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary, // ИЗМЕНЕНО: использование темы
                contentColor = MaterialTheme.colorScheme.onPrimary // ИЗМЕНЕНО: использование темы
            )
        ) { Text("Установить", fontWeight = FontWeight.Medium) }

        Spacer(Modifier.size(16.dp))

        Column(Modifier
            .padding(15.dp)
            .offset(x = 10.dp)) {
            Text("Описание", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface)
            Spacer(Modifier.size(8.dp))
            Text(text=description, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.size(16.dp))
            Row(
                modifier = Modifier.offset(y = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Категория:",
                    fontSize = 14.sp,
                    modifier = Modifier.alignByBaseline(),
                    color = MaterialTheme.colorScheme.onSurface
                )
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(start = 8.dp)
                        .alignByBaseline(),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        "футбол",
                        fontSize = 14.sp
                    )
                }
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(start = 8.dp)
                        .alignByBaseline(),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        "Веселье",
                        fontSize = 14.sp
                    )
                }
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(start = 8.dp)
                        .alignByBaseline(),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        "чилл",
                        fontSize = 14.sp
                    )
                }
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(start = 4.dp)
                        .alignByBaseline(),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary // ИЗМЕНЕНО: использование темы
                    )
                ) {
                    Text(
                        "баскетболл",
                        fontSize = 14.sp
                    )
                }

            }
        }

        // Горизонтальный скролл картинок с разными изображениями
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 30.dp)
                .padding(horizontal = 20.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            imageList.forEachIndexed { index, imageRes ->
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "скриншот ${index + 1}",
                    modifier = Modifier
                        .size(200.dp, 350.dp)
                        .padding(end = 10.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }

        // Блок комментариев
        Text(
            "Комментарии",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(20.dp, 50.dp, 20.dp, 10.dp)
        )

        OutlinedTextField(
            value = commentText,
            onValueChange = { commentText = it },
            placeholder = { Text("Напишите комментарий...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 10.dp)
        )

        Button(
            onClick = {
                if (commentText.isNotBlank()) {
                    comments.add(commentText)
                    commentText = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text("Отправить")
        }

        // Список комментариев (исправленный вариант)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 20.dp)
                .padding(20.dp)
        ) {
            if (comments.isEmpty()) {
                Text(
                    "Пока нет комментариев. Будьте первым!",
                    color = Color.Gray,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                comments.forEachIndexed { index, comment ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .combinedClickable(
                                onClick = {
                                    // Обычное нажатие - ничего не делаем
                                },
                                onLongClick = {
                                    // Долгое нажатие для удаления
                                    commentToDelete = index
                                    showDeleteDialog = true
                                }
                            )
                    ) {
                        Text(
                            text = comment,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                    }
                }
            }
        }
    }

    // Диалог удаления
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
                commentToDelete = -1
            },
            title = { Text("Удалить комментарий?") },
            text = { Text("Это действие нельзя отменить") },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (commentToDelete != -1) {
                            comments.removeAt(commentToDelete)
                        }
                        showDeleteDialog = false
                        commentToDelete = -1
                    }
                ) {
                    Text("Удалить")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        commentToDelete = -1
                    }
                ) {
                    Text("Отмена")
                }
            }
        )
    }
}