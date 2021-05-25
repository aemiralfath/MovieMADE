package com.aemiralfath.moviemade.detail

import androidx.lifecycle.ViewModel
import com.aemiralfath.core.domain.model.Movie
import com.aemiralfath.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}