package com.acuon.moviesapp.ui.favorite

import android.content.Context
import com.acuon.moviesapp.R
import com.acuon.moviesapp.common.ResultOf
import com.acuon.moviesapp.domain.model.MovieItem

data class FavoriteMoviesUIState(
    private val state: ResultOf<List<MovieItem>>
) {

    fun isLoading() = state is ResultOf.Loading

    fun isSuccess() = state is ResultOf.Success

    fun isError() = state is ResultOf.Error

    fun getErrorMessage(context: Context) = if (state is ResultOf.Error) {
        state.message ?: context.getString(R.string.something_went_wrong)
    } else ""

    fun isEmpty() = state is ResultOf.Success && state.data.isNullOrEmpty()

    fun getData() = if (state is ResultOf.Success) state.data else null

}