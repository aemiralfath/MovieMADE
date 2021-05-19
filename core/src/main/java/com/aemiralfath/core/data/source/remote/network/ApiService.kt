package com.aemiralfath.core.data.source.remote.network

import com.aemiralfath.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/3/discover/movie")
    suspend fun getListMovie(
        @Query("api_key") key: String
    ): ListMovieResponse

    @GET("/3/search/movie")
    suspend fun searchMovie(
        @Query("api_key") key: String,
        @Query("query") query: String
    ): ListMovieResponse

}