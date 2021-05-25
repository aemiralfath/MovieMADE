package com.aemiralfath.favorite

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aemiralfath.core.domain.model.Movie
import com.aemiralfath.core.domain.usecase.MovieUseCase
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    @Mock
    private lateinit var observer: Observer<List<Movie>>

    @Mock
    private lateinit var list: List<Movie>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(movieUseCase)
    }

    @Test
    fun setFavorite() {
        val dummyFavorite = list
        `when`(movieUseCase.getFavoriteMovie()).thenReturn(flowOf(dummyFavorite))
        viewModel.setFavorite().observeForever(observer)

        verify(movieUseCase).getFavoriteMovie()
        verify(observer).onChanged(dummyFavorite)
    }
}