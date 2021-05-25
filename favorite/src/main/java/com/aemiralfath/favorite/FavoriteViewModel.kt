package com.aemiralfath.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aemiralfath.core.domain.model.Movie
import com.aemiralfath.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    private val movie = movieUseCase

    fun setFavorite(): LiveData<List<Movie>> {
        return movie.getFavoriteMovie().asLiveData()
    }

}