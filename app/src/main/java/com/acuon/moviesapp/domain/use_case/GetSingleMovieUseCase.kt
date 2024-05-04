package com.acuon.moviesapp.domain.use_case

import com.acuon.moviesapp.common.ResultOf
import com.acuon.moviesapp.data.repository.FavoriteMovieRepositoryImpl
import com.acuon.moviesapp.data.repository.HomeRepositoryImpl
import com.acuon.moviesapp.domain.model.MovieItem
import com.acuon.moviesapp.domain.model.toMovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSingleMovieUseCase @Inject constructor(
    private val repository: HomeRepositoryImpl,
    private val favoritesRepository: FavoriteMovieRepositoryImpl
) {
    suspend operator fun invoke(trackId: Long, fromFavorite: Boolean): Flow<ResultOf<MovieItem?>> =
        flow {
            try {
                emit(ResultOf.Loading())
                val movie: MovieItem? = if (fromFavorite) {
                    favoritesRepository.getFavoriteMovieById(trackId)?.toMovieItem()
                } else {
                    repository.getMovieByID(trackId)
                }
                emit(ResultOf.Success(movie))
            } catch (e: Exception) {
                emit(ResultOf.Error(e.localizedMessage ?: "something went wrong"))
            }
        }.flowOn(Dispatchers.IO)
}