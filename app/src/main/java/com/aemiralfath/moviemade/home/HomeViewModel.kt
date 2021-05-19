package com.aemiralfath.moviemade.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aemiralfath.core.data.Resource
import com.aemiralfath.core.domain.model.Movie
import com.aemiralfath.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    private val movies = movieUseCase

    fun setMovies(
        sort: String,
        query: String
    ): LiveData<Resource<List<Movie>>> {
        return movies.getAllMovies(sort, query).asLiveData()
    }

}