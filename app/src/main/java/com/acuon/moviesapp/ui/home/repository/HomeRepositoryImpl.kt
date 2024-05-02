package com.acuon.moviesapp.ui.home.repository

import com.acuon.moviesapp.data.remote.MoviesApi
import com.acuon.moviesapp.domain.repository.IHomeRepository
import java.util.concurrent.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: MoviesApi,
) : IHomeRepository {

    suspend fun searchMovies(query: String) {
        return apiService.searchMovies(term = query)
    }

}