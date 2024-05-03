package com.acuon.moviesapp.ui.movie_detail.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.acuon.moviesapp.common.BaseViewModel
import com.acuon.moviesapp.common.ResultOf
import com.acuon.moviesapp.domain.model.MovieItem
import com.acuon.moviesapp.domain.use_case.AddMovieUseCase
import com.acuon.moviesapp.domain.use_case.GetSingleMovieUseCase
import com.acuon.moviesapp.domain.use_case.RemoveMovieUseCase
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

    fun getMovieById(trackId: Long) {
        execute {
            singleMovieUseCase.invoke(trackId).collect {
                _movieItem.value = it
            }
        }
    }

    fun updateFavoriteStatus(trackId: Long?, isFavorite: Boolean) {
        execute {
            updateFavoriteStatusUseCase.invoke(trackId!!, isFavorite).launchIn(viewModelScope)
        }
    }
}