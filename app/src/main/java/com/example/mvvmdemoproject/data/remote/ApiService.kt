package com.example.mvvmdemoproject.data.remote

import com.example.mvvmdemoproject.data.model.games.GameDataClass
import com.example.mvvmdemoproject.data.model.pagination.GameListResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET(NetworkingConstants.GAME_BY_ID)
    suspend fun getGames(
        @Query("key")
        key: String
    ): Response<GameDataClass>

    @GET(NetworkingConstants.GAMES_LIST)
    suspend fun getListOfGames(
        @Query("key")
        key: String,
        @Query("page")
        page: Int,
        @Query("page_size")
        pageSize: Int
    ): Response<GameListResponse>
}
