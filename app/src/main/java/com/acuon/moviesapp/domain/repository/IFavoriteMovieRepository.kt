package com.acuon.moviesapp.domain.repository

import com.acuon.moviesapp.domain.model.FavoriteMovieItem

interface IFavoriteMovieRepository {

    suspend fun addToFavorite(movie: FavoriteMovieItem)

    suspend fun removeFromFavorite(movie: FavoriteMovieItem)

    suspend fun getAllFavoriteMovies(): List<FavoriteMovieItem>

    suspend fun getFavoriteMovieById(trackId: Long): FavoriteMovieItem?

}