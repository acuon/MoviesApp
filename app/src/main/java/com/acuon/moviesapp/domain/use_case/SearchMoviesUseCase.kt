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

/**
 * Use case for searching movies and updating the local database with the search results.
 * This use case emits a flow of [ResultOf] that represents the result of the operation.
 *
 * @param repository The repository implementation of HomeRepository.
 * @param moviesDao The DAO object of MoviesDao(movie cache).
 */
class SearchMoviesUseCase @Inject constructor(
    private val repository: HomeRepositoryImpl,
    private val moviesDao: MoviesDao
) {

    /**
     * Searches for movies based on the provided query and updates the local database(cache) with the search results.
     *
     * @param query The searched query.
     * @return A flow emitting the result of the operation.
     */
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
            Timber.tag("SearchMoviesUseCase").d(e.toString())
        } catch (e: IOException) {
            emit(ResultOf.Error(e.localizedMessage ?: "Something went wrong"))
        }
    }.flowOn(Dispatchers.IO)
}
