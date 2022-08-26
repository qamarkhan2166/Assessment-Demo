package com.example.mvvmdemoproject.data.repository

import com.example.mvvmdemoproject.data.model.games.GameDataClass
import com.example.mvvmdemoproject.data.model.pagination.GameListResponse
import com.example.mvvmdemoproject.data.remote.ApiService
import com.example.mvvmdemoproject.data.remote.networkboundresource.Resource
import com.example.mvvmdemoproject.data.remote.networkboundresource.networkOnly
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getGames(): Flow<Resource<GameDataClass>> {
        return networkOnly(
            fetch = { apiService.getGames(DeveloperKey) },
            onFetchFailed = {
                it.printStackTrace()
            }
        ).flowOn(Dispatchers.IO)
    }

    suspend fun getListOFPages(page: Int): Flow<Resource<GameListResponse>> {
        return networkOnly(
            fetch = { apiService.getListOfGames(DeveloperKey, page, pageCount) },
            onFetchFailed = {
                it.printStackTrace()
            }
        ).flowOn(Dispatchers.IO)
    }

    companion object {
        private const val DeveloperKey = "f6eac999bfc94b4b938ad8210b295c0d"
        private const val pageCount = 10
    }
}
