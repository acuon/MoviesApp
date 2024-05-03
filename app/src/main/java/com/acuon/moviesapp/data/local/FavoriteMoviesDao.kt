package com.acuon.moviesapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.acuon.moviesapp.domain.model.FavoriteMovieItem
import com.acuon.moviesapp.domain.model.MovieItem

@Dao
interface FavoriteMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<FavoriteMovieItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: FavoriteMovieItem)

    @Delete
    fun removeMovie(movie: FavoriteMovieItem)

    @Query("SELECT * FROM FavoriteMoviesDatabase WHERE trackId = :trackId")
    fun getMovieById(trackId: Long): FavoriteMovieItem?

    @Query("SELECT * FROM FavoriteMoviesDatabase")
    fun getFavoriteMovies(): List<FavoriteMovieItem>
}