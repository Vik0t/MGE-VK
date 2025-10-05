package com.example.mge_vk

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class OnboardPage(
    val imageRes: Int, val title: String, val description: String
)

val onboardPagesList = listOf(
    OnboardPage(
        imageRes = R.drawable.ru2,
        title = "Добро пожаловать в RuStore",
        description = "Здесь вы можете скачать приложения"
    ), OnboardPage(
        imageRes = R.drawable.ru2,
        title = "Воспользуйся крутыми фичами",
        description = "можно поставить класс"
    ), OnboardPage(
        imageRes = R.drawable.ru1,
        title = "Начать использовать",
        description = ""
    )
)

@Composable
fun OnBoardImageView(modifier: Modifier = Modifier, imageRes: Int) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .align(Alignment.BottomCenter))
    }
}

@Composable
fun OnBoardDetails(
    modifier: Modifier = Modifier, currentPage: OnboardPage
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = currentPage.title,
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = currentPage.description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
@Composable
fun OnBoardNavButton(
    currentPage: Int,
    totalPages: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        val buttonText = if (currentPage < totalPages - 1) "Next" else "Get Started"
        Text(text = buttonText)
    }
}
@Composable
fun TabSelector(onboardPages: List<OnboardPage>, currentPage: Int, onTabSelected: (Int) -> Unit) {
    TabRow(
        selectedTabIndex = currentPage,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        onboardPages.forEachIndexed { index, _ ->
            Tab(selected = index == currentPage, onClick = {
                onTabSelected(index)
            }, modifier = Modifier.padding(16.dp), content = {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            color = if (index == currentPage) MaterialTheme.colorScheme.onPrimary
                            else Color.LightGray, shape = RoundedCornerShape(4.dp)
                        )
                )
            })
        }
    }
}

@Composable
fun OnboardScreen(
    navController: NavController,
    onComplete: () -> Unit
) {
    val onboardPages = onboardPagesList
    val currentPage = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        OnBoardImageView(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            imageRes = onboardPages[currentPage.value].imageRes
        )

        OnBoardDetails(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            currentPage = onboardPages[currentPage.value]
        )

        OnBoardNavButton(
            currentPage = currentPage.value,
            totalPages = onboardPages.size,
            onClick = {
                if (currentPage.value < onboardPages.size - 1) {
                    currentPage.value++
                } else {
                    onComplete() // 👈 This triggers saving + navigation
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )

        TabSelector(
            onboardPages = onboardPages,
            currentPage = currentPage.value
        ) { index ->
            currentPage.value = index
        }
    }
}