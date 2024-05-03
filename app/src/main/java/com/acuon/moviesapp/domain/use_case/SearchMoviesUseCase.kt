package com.acuon.moviesapp.domain.use_case

import com.acuon.moviesapp.common.ResultOf
import com.acuon.moviesapp.data.local.MoviesDao
import com.acuon.moviesapp.data.remote.dto.toMovieItem
import com.acuon.moviesapp.domain.model.MovieItem
import com.acuon.moviesapp.data.repository.HomeRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val repository: HomeRepositoryImpl,
    private val moviesDao: MoviesDao
) {

    suspend operator fun invoke(query: String): Flow<ResultOf<List<MovieItem>>> = flow {
        try {
            emit(ResultOf.Loading())
            val response = repository.searchMovies(query)
            val deferred = CoroutineScope(Dispatchers.IO).async {
                moviesDao.deleteAllMovies()
            }
            deferred.await()
            val cachedMovies: List<MovieItem> =
                response?.results?.map {
                    it?.toMovieItem() ?: MovieItem()
                } ?: emptyList()
            moviesDao.insertMovies(cachedMovies)
            emit(ResultOf.Success(cachedMovies))
        } catch (e: Exception) {
            emit(ResultOf.Error(e.localizedMessage ?: "Something went wrong"))
            Timber.tag("Response").d(e.toString())
        } catch (e: IOException) {
            emit(ResultOf.Error(e.localizedMessage ?: "Something went wrong"))
        }
    }.flowOn(Dispatchers.IO)
}