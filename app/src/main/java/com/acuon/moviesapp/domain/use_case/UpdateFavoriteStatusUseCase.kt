package com.acuon.moviesapp.domain.use_case

import com.acuon.moviesapp.common.ResultOf
import com.acuon.moviesapp.data.repository.FavoriteMovieRepositoryImpl
import com.acuon.moviesapp.data.repository.HomeRepositoryImpl
import com.acuon.moviesapp.domain.model.MovieItem
import com.acuon.moviesapp.domain.model.toFavoriteMovieItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Use case for updating the favorite status of a movie.
 * This use case emits a flow of [ResultOf] that represents the result of the operation.
 *
 * @param repository The repository implementation of HomeRepository.
 * @param favoriteRepository The repository implementation of FavoriteMovieRepository.
 */
class UpdateFavoriteStatusUseCase @Inject constructor(
    private val repository: HomeRepositoryImpl,
    private val favoriteRepository: FavoriteMovieRepositoryImpl
) {

    /**
     * Updates the favorite status of a movie and notifies the repositories accordingly.
     *
     * @param movieItem The movie object for which the status is to be updated in database.
     * @param isFavorite Whether to mark the movie as favorite or not.
     * @return A flow emitting the result of the operation.
     */
    suspend operator fun invoke(movieItem: MovieItem, isFavorite: Boolean): Flow<ResultOf<String>> =
        flow {
            try {
                emit(ResultOf.Loading())
                val deferred = CoroutineScope(Dispatchers.IO).async {
                    repository.updateFavoriteStatus(movieItem.trackId!!, isFavorite)
                }
                if (isFavorite) {
                    val movie = movieItem.toFavoriteMovieItem()
                    movie.isFavorite = true
                    favoriteRepository.addToFavorite(movie)
                } else {
                    favoriteRepository.removeFromFavorite(movieItem.toFavoriteMovieItem())
                }
                deferred.await()
                emit(ResultOf.Success("success"))
            } catch (e: Exception) {
                emit(ResultOf.Error(e.localizedMessage ?: "something went wrong"))
            }
        }.flowOn(Dispatchers.IO)
}
