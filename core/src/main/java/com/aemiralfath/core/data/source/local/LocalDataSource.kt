package com.aemiralfath.core.data.source.local

import androidx.sqlite.db.SupportSQLiteQuery
import com.aemiralfath.core.data.source.local.entity.MovieEntity
import com.aemiralfath.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }

    fun getMovieQuery(query: SupportSQLiteQuery): Flow<List<MovieEntity>> =
        movieDao.getMovieQuery(query)

}