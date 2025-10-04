package com.example.mge_vk

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File

object ApkInstaller {
    const val REQUEST_CODE_UNKNOWN_APP = 1001

    fun installApk(activity: Activity, apkFile: File): Boolean {
        if (!apkFile.exists()) {
            Toast.makeText(activity, "APK file not found", Toast.LENGTH_SHORT).show()
            return false
        }

        // On Android 8.0+ (API 26+), check install permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!activity.packageManager.canRequestPackageInstalls()) {
                // Launch settings to request permission
                val intent = Intent(android.provider.Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES).apply {
                    data = Uri.parse("package:${activity.packageName}")
                }
                activity.startActivityForResult(intent, REQUEST_CODE_UNKNOWN_APP)
                return true // Permission request in progress
            }
        }

        // Proceed to install
        startInstallation(activity, apkFile)
        return true
    }

    private fun startInstallation(activity: Activity, apkFile: File) {
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(
                activity,
                "${activity.packageName}.fileprovider",
                apkFile
            )
        } else {
            Uri.fromFile(apkFile)
        }

        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/vnd.android.package-archive")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        activity.startActivity(intent)
    }


    fun isInstallPermissionResult(requestCode: Int): Boolean {
        return requestCode == REQUEST_CODE_UNKNOWN_APP
    }

    fun handlePermissionResult(activity: Activity, apkFile: File) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (activity.packageManager.canRequestPackageInstalls()) {
                startInstallation(activity, apkFile)
            } else {
                Toast.makeText(activity, "Installation permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}