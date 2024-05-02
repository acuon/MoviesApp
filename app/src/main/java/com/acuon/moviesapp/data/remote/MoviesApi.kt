package com.acuon.moviesapp.data.remote

import com.acuon.moviesapp.common.Endpoints
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
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 10
    )
}