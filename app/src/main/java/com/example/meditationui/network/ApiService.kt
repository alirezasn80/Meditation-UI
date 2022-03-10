package com.example.meditationui.network

import com.example.meditationui.network.model.MusicPlayListDto

import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("music")
    suspend fun getMusicPlayList(
        @Query("page") page: Int = 1
    ): MusicPlayListDto
}