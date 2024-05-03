package com.acuon.moviesapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.acuon.moviesapp.domain.model.MovieItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieItem)

    @Delete
    fun removeMovie(movie: MovieItem)

    // Custom insert method
    @Transaction
    fun insertOrUpdateMovies(movies: List<MovieItem>) {
        movies.forEach { movie ->
            val existing = getMovieById(movie.trackId!!)

            if (existing != null) {
                // Check if the existing movie is marked as a favorite
                val isFavorite = existing.isFavorite

                // Update the new movie's favorite status accordingly
                movie.isFavorite = isFavorite
            }

            // Insert or update the movie
            insertMovies(listOf(movie))
        }
    }

    @Query("SELECT * FROM MoviesDatabase WHERE trackId = :trackId")
    fun getMovieById(trackId: Long): MovieItem?

    @Query("SELECT * FROM MoviesDatabase ORDER BY name")
    fun getCachedMovies(): List<MovieItem>

    @Query("DELETE FROM MoviesDatabase")
    suspend fun deleteAllMovies()

    @Query("UPDATE MoviesDatabase SET isFavorite = :isFavorite WHERE trackId = :trackId")
    fun updateFavoriteStatus(trackId: Long, isFavorite: Boolean)

}