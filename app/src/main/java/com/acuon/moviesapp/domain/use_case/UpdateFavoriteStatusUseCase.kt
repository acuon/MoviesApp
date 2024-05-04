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

class UpdateFavoriteStatusUseCase @Inject constructor(
    private val repository: HomeRepositoryImpl,
    private val favoriteRepository: FavoriteMovieRepositoryImpl
) {
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