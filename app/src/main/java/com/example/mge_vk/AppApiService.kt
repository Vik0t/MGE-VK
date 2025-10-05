package com.example.mge_vk

import retrofit2.Response
import retrofit2.http.GET

interface AppApiService {
    @GET("apps.json") // ← add .json
    suspend fun getApps(): Response<List<RemoteAppData>>
}