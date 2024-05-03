package com.acuon.moviesapp.data.remote

import com.acuon.moviesapp.common.Endpoints
import com.acuon.moviesapp.data.remote.dto.MoviesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    /**
     * @param term the name of the movie to search.
     * @param limit the number of items to download.
     * @param offset the next batch of download.
     *
     * @see
     */
    @GET(Endpoints.SEARCH)
    suspend fun searchMovies(
        @Query("term") term: String,
        @Query("media") media: String = "movie",
        @Query("country") country: String = "au",
    ): MoviesResponseDto?
}