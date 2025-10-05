package com.example.mge_vk

import ApkInstaller
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.edit
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mge_vk.ui.theme.MGEVKTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class MainActivity : ComponentActivity() {

    private fun isFirstLaunch(): Boolean {
        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
        val isFirst = prefs.getBoolean("is_first_launch", true)
        println("DEBUG: isFirstLaunch = $isFirst") // Check Logcat
        return isFirst
    }

    private fun markOnboardingCompleted() {
        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
        prefs.edit { putBoolean("is_first_launch", false) }
        println("DEBUG: Onboarding marked as completed") // Check Logcat
    }
    private var remoteAppsList by mutableStateOf<List<AppData>>(emptyList())
    private var isDataLoaded by mutableStateOf(false)
    private var pendingApkFile: File? = null
    private val installPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // After user returns from settings
        pendingApkFile?.let { apkFile ->
            pendingApkFile = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (packageManager.canRequestPackageInstalls()) {
                    ApkInstaller.installApk(this, apkFile)
                } else {
                    Toast.makeText(this, "Разрешение на установку не предоставлено", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        ensureApksExist()
        fetchDataFromServer()

        enableEdgeToEdge()

        val startDestination = if (isFirstLaunch()) {
            "OnboardScreen"
        } else {
            "HomeScreen/Все"
        }

        setContent {
            MGEVKTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ) {
                    composable("HomeScreen/{category}") {
                        backStackEntry ->
                        val category = backStackEntry.arguments?.getString("category") ?: "Все"
                        HomeScreen(
                            navController = navController,
                            onInstallApp = { appId ->
                                installAppByAppId(appId)
                            },
                            apps = remoteAppsList,
                            category
                        )
                    }
                    composable("CategoriesScreen") {
                        CategoryScreen(navController,apps = remoteAppsList)
                    }
                    composable("OnboardScreen") {
                        OnboardScreen(
                            navController = navController,
                            onComplete = {
                                markOnboardingCompleted()
                                navController.navigate("HomeScreen") {
                                    popUpTo("OnboardScreen") { inclusive = true }
                                }
                            }
                        )
                    }
                    composable("CardScreen/{appId}") { backStackEntry ->
                        val appId = backStackEntry.arguments?.getString("appId")?.toIntOrNull()
                        CardScreen(
                            navController = navController,
                            appId = appId,
                            onInstallApp = { appId ->
                                installAppByAppId(appId)
                            },
                            apps = remoteAppsList
                        )
                    }
                }
            }
        }
    }
    private fun fetchDataFromServer() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiService.getApps()
                if (response.isSuccessful) {
                    val remoteList = response.body() ?: emptyList()
                    // Convert RemoteAppData → AppData (using local icons)
                    val localList = remoteList.map { remote ->
                        // Map icon based on appId or apkFilename
                        val iconRes = when (remote.apkFilename) {
                            "buz.apk" -> R.drawable.buz
                            "max.apk" -> R.drawable.max
                            "vk.apk" -> R.drawable.vk
                            else -> R.drawable.nichosi
                        }
                        AppData(
                            appId = remote.appId,
                            appName = remote.appName,
                            devName = remote.devName,
                            appIcon = iconRes,
                            tag = remote.tag,
                            stars = remote.stars,
                            ageRating = remote.ageRating,
                            description = remote.description
                        )
                    }

                    withContext(Dispatchers.Main) {
                        remoteAppsList = localList // ✅ Now this triggers recomposition
                        isDataLoaded = true
                    }
                } else {
                    // Fallback to hardcoded list on error
                    withContext(Dispatchers.Main) {
                        remoteAppsList = appsList // ✅ Also triggers update
                        Toast.makeText(this@MainActivity, "Failed to load apps", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    remoteAppsList = appsList
                    Toast.makeText(this@MainActivity, "Network error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun installAppByAppId(appId: Int) {
        val apkFileName = getAppApkFileName(appId)
        if (apkFileName == null) {
            Toast.makeText(this, "Установка недоступна", Toast.LENGTH_SHORT).show()
            return
        }

        val apkFile = File(filesDir, "apks/$apkFileName")
        if (!apkFile.exists()) {
            Toast.makeText(this, "APK не найден", Toast.LENGTH_SHORT).show()
            return
        }

        // Check if we can install
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!packageManager.canRequestPackageInstalls()) {
                // Store APK for later
                pendingApkFile = apkFile
                // Launch permission request
                val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES).apply {
                    data = Uri.parse("package:$packageName")
                }
                installPermissionLauncher.launch(intent)
                return
            }
        }

        // Install immediately if permission is granted (or not needed)
        ApkInstaller.installApk(this, apkFile)
    }

    private fun getAppApkFileName(appId: Int): String? = when (appId) {
        1001 -> "buz.apk"
        1002 -> "max.apk"
        1003 -> "vk.apk"
        else -> null
    }

    private fun ensureApksExist() {
        val apkFilenames = listOf("buz.apk", "max.apk", "vk.apk") // just filenames
        val apksDir = File(filesDir, "apks")
        if (!apksDir.exists()) {
            apksDir.mkdirs()
        }

        for (filename in apkFilenames) {
            val outFile = File(apksDir, filename)
            if (!outFile.exists()) {
                try {
                    assets.open("apks/$filename").use { input ->
                        outFile.outputStream().use { output ->
                            input.copyTo(output)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, "Не получилось загрузить $filename", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}