package com.acuon.moviesapp.ui.home.viewmodel

import androidx.lifecycle.viewModelScope
import com.acuon.moviesapp.common.BaseViewModel
import com.acuon.moviesapp.common.ResultOf
import com.acuon.moviesapp.domain.model.MovieItem
import com.acuon.moviesapp.domain.use_case.GetCachedMoviesUseCase
import com.acuon.moviesapp.domain.use_case.SearchMoviesUseCase
import com.acuon.moviesapp.domain.use_case.UpdateFavoriteStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val cachedMoviesUseCase: GetCachedMoviesUseCase,
    private val updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase
) :
    BaseViewModel() {

    init {
        searchMovies("")
    }

    private val _moviesListState = MutableStateFlow<ResultOf<List<MovieItem>>>(ResultOf.Loading())
    val moviesListState: StateFlow<ResultOf<List<MovieItem>>>
        get() = _moviesListState

    private var searchJob: Job? = null

    fun searchMovies(query: String) {
        searchJob?.cancel()
        execute { delay(200) }
        searchJob = execute {
            searchMoviesUseCase(query).map {
                _moviesListState.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun getCachedResponse() {
        execute {
            cachedMoviesUseCase.invoke().map {
                _moviesListState.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun updateFavoriteStatus(movieItem: MovieItem?, isFavorite: Boolean) {
        execute {
            updateFavoriteStatusUseCase.invoke(movieItem!!, isFavorite).launchIn(viewModelScope)
        }
    }

    fun cancelSearch() {
        searchJob?.cancel()
    }

}