package com.example.meditationui.pagination


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.meditationui.network.ApiService
import com.example.meditationui.network.model.Music
import com.example.meditationui.network.model.toMusic
import retrofit2.HttpException
import java.io.IOException

class UserSource(
    private val apiService: ApiService
) : PagingSource<Int, Music>() {

    override fun getRefreshKey(state: PagingState<Int, Music>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Music> {

        return try {
            val currentPage = params.key ?: 1
            val userList = apiService.getMusicPlayList(currentPage)

            LoadResult.Page(
                data = userList.music_list.map { it.toMusic() },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (userList.music_list.isEmpty()) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}