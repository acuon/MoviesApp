package com.acuon.moviesapp.domain.use_case

import com.acuon.moviesapp.common.ResultOf
import com.acuon.moviesapp.domain.model.MovieItem
import com.acuon.moviesapp.data.repository.HomeRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class RemoveMovieUseCase @Inject constructor(private val repository: HomeRepositoryImpl) {
    suspend operator fun invoke(movie: MovieItem): Flow<ResultOf<String>> = flow {
        try {
            emit(ResultOf.Loading())
            repository.removeMovieFromFavorite(movie)
            emit(ResultOf.Success("success"))
        } catch (e: Exception) {
            emit(ResultOf.Error(e.localizedMessage ?: "Something went wrong"))
            Timber.tag("Response").d(e.toString())
        } catch (e: IOException) {
            emit(ResultOf.Error(e.localizedMessage ?: "Something went wrong"))
        }
    }.flowOn(Dispatchers.IO)
}