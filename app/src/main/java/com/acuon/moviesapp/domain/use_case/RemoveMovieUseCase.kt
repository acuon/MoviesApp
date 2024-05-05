package com.acuon.moviesapp.domain.use_case

import com.acuon.moviesapp.common.ResultOf
import com.acuon.moviesapp.data.repository.FavoriteMovieRepositoryImpl
import com.acuon.moviesapp.domain.model.MovieItem
import com.acuon.moviesapp.domain.model.toFavoriteMovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

/**
 * Use case for removing a movie from the list of favorite movies.
 * This use case emits a flow of [ResultOf] that represents the result of the operation.
 *
 * @param repository The repository implementation of FavoriteMoviesRepository.
 */
class RemoveMovieUseCase @Inject constructor(private val repository: FavoriteMovieRepositoryImpl) {

    /**
     * Removes a movie from the list of favorite movies.
     *
     * @param movie the movie object or row to be removed from Database.
     * @return A flow emitting the result of the operation.
     */
    suspend operator fun invoke(movie: MovieItem): Flow<ResultOf<String>> = flow {
        try {
            emit(ResultOf.Loading())
            repository.removeFromFavorite(movie.toFavoriteMovieItem())
            emit(ResultOf.Success("success"))
        } catch (e: Exception) {
            emit(ResultOf.Error(e.localizedMessage ?: "Something went wrong"))
            Timber.tag("RemoveMovieUseCase").d(e.toString())
        } catch (e: IOException) {
            emit(ResultOf.Error(e.localizedMessage ?: "Something went wrong"))
        }
    }.flowOn(Dispatchers.IO)
}
