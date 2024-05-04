package com.acuon.moviesapp.ui.movie_detail.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.acuon.moviesapp.common.BaseViewModel
import com.acuon.moviesapp.common.ResultOf
import com.acuon.moviesapp.domain.model.MovieItem
import com.acuon.moviesapp.domain.use_case.GetSingleMovieUseCase
import com.acuon.moviesapp.domain.use_case.UpdateFavoriteStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val singleMovieUseCase: GetSingleMovieUseCase,
    private val updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase,
) : BaseViewModel() {

    private val _movieItem = MutableLiveData<ResultOf<MovieItem?>>()
    val movieItem: LiveData<ResultOf<MovieItem?>>
        get() = _movieItem

    val trailerUrl = ObservableField<String>()
    val isFavorite = ObservableField<Boolean>()
    val movie = ObservableField<MovieItem>()

    fun getMovieById(trackId: Long, fromFavorite: Boolean) {
        execute {
            singleMovieUseCase.invoke(trackId, fromFavorite).collect {
                _movieItem.value = it
            }
        }
    }

    fun updateFavoriteStatus(movieItem: MovieItem?, favorite: Boolean) {
        execute {
            updateFavoriteStatusUseCase.invoke(movieItem!!, favorite).launchIn(viewModelScope)
            isFavorite.set(isFavorite.get() != true)
        }
    }
}