package com.acuon.moviesapp.ui.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.acuon.moviesapp.common.ResultOf
import com.acuon.moviesapp.domain.model.MovieItem
import com.acuon.moviesapp.domain.use_case.GetCachedMoviesUseCase
import com.acuon.moviesapp.domain.use_case.SearchMoviesUseCase
import com.acuon.moviesapp.domain.use_case.UpdateFavoriteStatusUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var searchMoviesUseCase: SearchMoviesUseCase
    private lateinit var cachedMoviesUseCase: GetCachedMoviesUseCase
    private lateinit var updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        searchMoviesUseCase = mock(SearchMoviesUseCase::class.java)
        cachedMoviesUseCase = mock(GetCachedMoviesUseCase::class.java)
        updateFavoriteStatusUseCase = mock(UpdateFavoriteStatusUseCase::class.java)

        viewModel = HomeViewModel(searchMoviesUseCase, cachedMoviesUseCase, updateFavoriteStatusUseCase)
    }

    @Test
    fun `searchMovies should update moviesListState with search results`() = runBlockingTest {
        // Given
        val query = "query"
        val movieList = listOf<MovieItem>(MovieItem(123, "Avengers", "", "", "", "01-02-2024", 135425, "13+", "", "actionn", 12.34, "AUD", true))
        `when`(searchMoviesUseCase.invoke(query)).thenReturn(flow { emit(ResultOf.Success(movieList)) })

        // When
        viewModel.searchMovies(query)

        // Then
        val result = viewModel.moviesListState.first()
        assertEquals(ResultOf.Success(movieList), result)
    }

    @Test
    fun `getCachedResponse should update moviesListState with cached response`() = runBlockingTest {
        // Given
        val cachedMovieList = listOf<MovieItem>(MovieItem(123, "Avengers", "", "", "", "01-02-2024", 135425, "13+", "", "actionn", 12.34, "AUD", true))
        `when`(cachedMoviesUseCase.invoke()).thenReturn(flow { emit(ResultOf.Success(cachedMovieList)) })

        // When
        viewModel.getCachedResponse()

        // Then
        val result = viewModel.moviesListState.first()
        assertEquals(ResultOf.Success(cachedMovieList), result)
    }

    @Test
    fun `updateFavoriteStatus should invoke UpdateFavoriteStatusUseCase`() = runBlockingTest {
        // Given
        val movieItem = mock(MovieItem::class.java)
        val isFavorite = true

        // When
        viewModel.updateFavoriteStatus(movieItem, isFavorite)

        // Then
        verify(updateFavoriteStatusUseCase).invoke(movieItem, isFavorite)
    }

}
