package com.aemiralfath.moviemade.detail

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aemiralfath.core.domain.model.Movie
import com.aemiralfath.core.domain.usecase.MovieUseCase
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private var dummyMovie = listOf(
        Movie(
            1,
            "Mortal",
            "hai",
            "en",
            "path",
            "today",
            9.0,
            true,
            1000,
            true
        )
    )

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieUseCase)
    }

    @Test
    fun setFavoriteMovie() {
        val movie = dummyMovie[0]
        doNothing().`when`(movieUseCase).setFavoriteMovie(movie, !movie.isFavorite)

        viewModel.setFavoriteMovie(movie, !movie.isFavorite)
        verify(movieUseCase).setFavoriteMovie(movie, !movie.isFavorite)
    }
}