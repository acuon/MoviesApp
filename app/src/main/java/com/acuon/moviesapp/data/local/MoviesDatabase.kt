package com.acuon.moviesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acuon.moviesapp.domain.model.FavoriteMovieItem
import com.acuon.moviesapp.domain.model.MovieItem

@Database(entities = [MovieItem::class, FavoriteMovieItem::class], version = 2, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
    abstract fun getFavoriteMoviesDao(): FavoriteMoviesDao
}