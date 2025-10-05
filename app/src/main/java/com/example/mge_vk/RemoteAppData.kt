package com.example.mge_vk

import com.google.gson.annotations.SerializedName

data class RemoteAppData(
    @SerializedName("id") val appId: Int,
    @SerializedName("name") val appName: String,
    @SerializedName("developer") val devName: String,
    @SerializedName("icon_url") val iconUrl: String, // optional: you can keep local icons
    @SerializedName("tag") val tag: String,
    @SerializedName("stars") val stars: Double,
    @SerializedName("age_rating") val ageRating: Int,
    @SerializedName("description") val description: String,
    @SerializedName("apk_filename") val apkFilename: String // e.g., "buz.apk"
)