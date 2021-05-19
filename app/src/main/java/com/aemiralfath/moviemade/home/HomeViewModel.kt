package com.aemiralfath.moviemade.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aemiralfath.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val movies = movieUseCase.getAllMovies().asLiveData()

}