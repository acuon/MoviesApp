package com.acuon.moviesapp.ui.favorite.viewmodel

import androidx.lifecycle.viewModelScope
import com.acuon.moviesapp.common.BaseViewModel
import com.acuon.moviesapp.common.ResultOf
import com.acuon.moviesapp.domain.model.MovieItem
import com.acuon.moviesapp.domain.model.toMovieItem
import com.acuon.moviesapp.domain.use_case.GetFavoriteMoviesUseCase
import com.acuon.moviesapp.domain.use_case.RemoveMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val removeMovieUseCase: RemoveMovieUseCase
) :
    BaseViewModel() {
    init {
        getFavoriteMovies()
    }

    private val _moviesListState = MutableStateFlow<ResultOf<List<MovieItem>>>(ResultOf.Loading())
    val moviesListState: StateFlow<ResultOf<List<MovieItem>>>
        get() = _moviesListState

    fun getFavoriteMovies() {
        execute {
            getFavoriteMoviesUseCase.invoke().map {
                _moviesListState.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun removeFromFavorite(movie: MovieItem) {
        execute {
            removeMovieUseCase.invoke(movie).launchIn(viewModelScope)
        }
    }

}