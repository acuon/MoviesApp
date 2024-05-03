package com.acuon.moviesapp.domain.use_case

import com.acuon.moviesapp.common.ResultOf
import com.acuon.moviesapp.data.repository.FavoriteMovieRepositoryImpl
import com.acuon.moviesapp.domain.model.FavoriteMovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(private val repository: FavoriteMovieRepositoryImpl) {
    suspend operator fun invoke(): Flow<ResultOf<List<FavoriteMovieItem>>> = flow {
        try {
            emit(ResultOf.Loading())
            val response = repository.getAllFavoriteMovies()
            emit(ResultOf.Success(response))
        } catch (e: Exception) {
            emit(ResultOf.Error(e.message ?: "something went wrong"))
        }
    }.flowOn(Dispatchers.IO)
}