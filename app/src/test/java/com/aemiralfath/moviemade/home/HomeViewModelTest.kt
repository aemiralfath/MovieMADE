package com.aemiralfath.moviemade.home

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aemiralfath.core.data.Resource
import com.aemiralfath.core.domain.model.Movie
import com.aemiralfath.core.domain.usecase.MovieUseCase
import com.aemiralfath.core.utils.SortUtils
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
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var query: String
    private lateinit var sort: String

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    @Mock
    private lateinit var observer: Observer<Resource<List<Movie>>>

    @Mock
    private lateinit var list: List<Movie>

    @Before
    fun setUp() {
        viewModel = HomeViewModel(movieUseCase)
        sort = SortUtils.NEWEST
        query = ""
    }

    @Test
    fun setMovies() {
        val dummyMovies = Resource.Success(list)
        `when`(movieUseCase.getAllMovies(sort, query)).thenReturn(flowOf(dummyMovies))
        viewModel.setMovies(sort, query).observeForever(observer)

        verify(movieUseCase).getAllMovies(sort, query)
        verify(observer).onChanged(dummyMovies)
    }
}