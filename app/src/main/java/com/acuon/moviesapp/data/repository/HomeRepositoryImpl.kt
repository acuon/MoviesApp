package com.acuon.moviesapp.data.repository

import com.acuon.moviesapp.data.local.MoviesDao
import com.acuon.moviesapp.data.remote.MoviesApi
import com.acuon.moviesapp.data.remote.dto.MoviesResponseDto
import com.acuon.moviesapp.domain.model.MovieItem
import com.acuon.moviesapp.domain.repository.IHomeRepository
import javax.inject.Inject

/**
 * Repository implementation for Movies Cache
 */
class HomeRepositoryImpl @Inject constructor(
    private val apiService: MoviesApi,
    private val moviesDao: MoviesDao
) : IHomeRepository {

    override suspend fun searchMovies(query: String): MoviesResponseDto? {
        return apiService.searchMovies(term = query)
    }

    override suspend fun getMovieByID(trackId: Long): MovieItem? {
        return moviesDao.getMovieById(trackId)
    }

    override suspend fun getCachedMovies(): List<MovieItem> {
        return moviesDao.getCachedMovies()
    }

    override suspend fun updateFavoriteStatus(trackId: Long, isFavorite: Boolean) {
        moviesDao.updateFavoriteStatus(trackId, isFavorite)
    }

}