package com.aemiralfath.core.utils

import com.aemiralfath.core.data.source.local.entity.MovieEntity
import com.aemiralfath.core.data.source.remote.response.MovieResponse
import com.aemiralfath.core.domain.model.Movie

object DataMapper {

    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val list = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                title = it.title,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                adult = it.adult,
                voteCount = it.voteCount,
                isFavorite = false
            )

            list.add(movie)
        }
        return list
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                adult = it.adult,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        title = input.title,
        overview = input.overview,
        originalLanguage = input.originalLanguage,
        backdropPath = input.backdropPath,
        releaseDate = input.releaseDate,
        popularity = input.popularity,
        adult = input.adult,
        voteCount = input.voteCount,
        isFavorite = input.isFavorite
    )

}