package com.example.meditationui.interactors.music

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.meditationui.R
import com.example.meditationui.network.ApiService
import com.example.meditationui.network.model.Music
import com.example.meditationui.network.model.toMusic
import com.example.meditationui.pagination.UserSource
import com.example.meditationui.ui.util.DataState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.lang.Exception

class GetMusicPlayList(
    private val apiService: ApiService
) {

    @ExperimentalPagingApi
    fun execute() = flow<DataState<Pager<Int, Music>>> {
        emit(DataState.Loading(ProgressBarState.Loading))

        try {
            val data = Pager(PagingConfig(pageSize = 3)) { UserSource(apiService) }

            emit(DataState.Data(data))

        } catch (e: Exception) {
            Log.i("AppDebug", "get music play list : Error -> ${e.message}")
        } finally {
            emit(DataState.Loading())
        }

    }
}