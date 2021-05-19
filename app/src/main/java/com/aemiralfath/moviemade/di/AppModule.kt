package com.aemiralfath.moviemade.di

import com.aemiralfath.core.domain.usecase.MovieInteractor
import com.aemiralfath.core.domain.usecase.MovieUseCase
import com.aemiralfath.moviemade.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}