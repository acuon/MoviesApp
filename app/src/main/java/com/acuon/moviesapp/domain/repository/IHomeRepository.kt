package com.acuon.moviesapp.domain.repository

import com.acuon.moviesapp.data.remote.dto.MoviesResponseDto
import com.acuon.moviesapp.domain.model.FavoriteMovieItem
import com.acuon.moviesapp.domain.model.MovieItem

/**
 * Interface defining operations for managing movies from Network call or Movie cache
 */
interface IHomeRepository {
    suspend fun searchMovies(query: String): MoviesResponseDto?

    suspend fun getMovieByID(trackId: Long): MovieItem?

    suspend fun getCachedMovies():List<MovieItem>

    suspend fun updateFavoriteStatus(trackId: Long, isFavorite: Boolean)
}