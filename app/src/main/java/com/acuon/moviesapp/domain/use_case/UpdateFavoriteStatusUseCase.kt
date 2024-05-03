package com.acuon.moviesapp.domain.use_case

import com.acuon.moviesapp.common.ResultOf
import com.acuon.moviesapp.data.repository.HomeRepositoryImpl
import com.acuon.moviesapp.domain.model.MovieItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UpdateFavoriteStatusUseCase @Inject constructor(private val repository: HomeRepositoryImpl) {
    suspend operator fun invoke(trackId: Long, isFavorite: Boolean): Flow<ResultOf<String>> = flow {
        try {
            emit(ResultOf.Loading())
            val deferred = CoroutineScope(Dispatchers.IO).async {
                repository.updateFavoriteStatus(trackId, isFavorite)
            }
            deferred.await()
            emit(ResultOf.Success("success"))
        } catch (e: Exception) {
            emit(ResultOf.Error(e.localizedMessage ?: "something went wrong"))
        }
    }.flowOn(Dispatchers.IO)
}