package com.acuon.moviesapp.domain.repository

import com.acuon.moviesapp.data.remote.dto.MoviesResponseDto
import com.acuon.moviesapp.domain.model.MovieItem
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {
    suspend fun searchMovies(query: String): MoviesResponseDto?

    suspend fun getMovieByID(trackId: Long): MovieItem?

    suspend fun addMovieToFavorites(movie: MovieItem)

    suspend fun removeMovieFromFavorite(movie: MovieItem)

    suspend fun getCachedMovies(): List<MovieItem>

    suspend fun updateFavoriteStatus(trackId: Long, isFavorite: Boolean)
}