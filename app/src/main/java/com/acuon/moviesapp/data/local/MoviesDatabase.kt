package com.acuon.moviesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acuon.moviesapp.domain.model.FavoriteMovieItem
import com.acuon.moviesapp.domain.model.MovieItem

/**
 * RoomDatabase class for storing Movies data
 * @param entities - array of entity tables to be used in database
 * @param version - version of the database schema
 * @param exportSchema - to export the schema to a file, Default is false
 */
@Database(entities = [MovieItem::class, FavoriteMovieItem::class], version = 2, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    /**
     * return MoviesDao object
     */
    abstract fun getMoviesDao(): MoviesDao

    /**
     * return FavoriteMoviesDao object
     */
    abstract fun getFavoriteMoviesDao(): FavoriteMoviesDao
}