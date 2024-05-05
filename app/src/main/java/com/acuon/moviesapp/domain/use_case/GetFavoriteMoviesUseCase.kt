package com.acuon.moviesapp.domain.use_case

import com.acuon.moviesapp.common.ResultOf
import com.acuon.moviesapp.data.repository.FavoriteMovieRepositoryImpl
import com.acuon.moviesapp.domain.model.FavoriteMovieItem
import com.acuon.moviesapp.domain.model.MovieItem
import com.acuon.moviesapp.domain.model.toMovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Use case for retrieving favorite movies from the repository.
 * This use case emits a flow of [ResultOf] that represents the result of the operation.
 *
 * @param repository The repository implementation of FavoriteMovieRepository.
 */
class GetFavoriteMoviesUseCase @Inject constructor(private val repository: FavoriteMovieRepositoryImpl) {

    /**
     * Retrieves favorite movies from the repository as a flow of [ResultOf].
     *
     * @return A flow emitting the result of the operation.
     */
    suspend operator fun invoke(): Flow<ResultOf<List<MovieItem>>> = flow {
        try {
            emit(ResultOf.Loading())
            val response = repository.getAllFavoriteMovies().map { it.toMovieItem() }
            emit(ResultOf.Success(response))
        } catch (e: Exception) {
            emit(ResultOf.Error(e.message ?: "something went wrong"))
        }
    }.flowOn(Dispatchers.IO)
}