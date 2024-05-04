package com.acuon.moviesapp.data.repository

import com.acuon.moviesapp.data.local.FavoriteMoviesDao
import com.acuon.moviesapp.domain.model.FavoriteMovieItem
import com.acuon.moviesapp.domain.repository.IFavoriteMovieRepository
import javax.inject.Inject

class FavoriteMovieRepositoryImpl @Inject constructor(
    private val favoriteMoviesDao: FavoriteMoviesDao
) :
    IFavoriteMovieRepository {
    override suspend fun addToFavorite(movie: FavoriteMovieItem) {
        favoriteMoviesDao.insertMovie(movie)
    }

    override suspend fun removeFromFavorite(movie: FavoriteMovieItem) {
        favoriteMoviesDao.deleteFromDatabase(movie.trackId!!)
    }

    override suspend fun getAllFavoriteMovies(): List<FavoriteMovieItem> {
        return favoriteMoviesDao.getFavoriteMovies()
    }

    override suspend fun getFavoriteMovieById(trackId: Long): FavoriteMovieItem? {
        return favoriteMoviesDao.getMovieById(trackId)
    }
}