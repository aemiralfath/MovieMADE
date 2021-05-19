package com.aemiralfath.core.data.source.remote

import android.util.Log
import com.aemiralfath.core.BuildConfig.TMDB_API_KEY
import com.aemiralfath.core.data.source.remote.network.ApiResponse
import com.aemiralfath.core.data.source.remote.network.ApiService
import com.aemiralfath.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    private val token = TMDB_API_KEY

    suspend fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getListMovie(TMDB_API_KEY)
                val dataArray = response.results

                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}