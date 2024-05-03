package com.acuon.moviesapp.domain.use_case

import com.acuon.moviesapp.common.ResultOf
import com.acuon.moviesapp.data.repository.HomeRepositoryImpl
import com.acuon.moviesapp.domain.model.MovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSingleMovieUseCase @Inject constructor(private val repository: HomeRepositoryImpl) {
    suspend operator fun invoke(trackId: Long): Flow<ResultOf<MovieItem?>> = flow {
        try {
            emit(ResultOf.Loading())
            emit(ResultOf.Success(repository.getMovieByID(trackId)))
        } catch (e: Exception) {
            emit(ResultOf.Error(e.localizedMessage ?: "something went wrong"))
        }
    }.flowOn(Dispatchers.IO)
}