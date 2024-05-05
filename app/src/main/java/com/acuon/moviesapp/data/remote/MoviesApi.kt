package com.acuon.moviesapp.data.remote

import com.acuon.moviesapp.common.Endpoints
import com.acuon.moviesapp.data.remote.dto.MoviesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    /**
     * @param term the name of the movie to search
     * @param media the media type to be fetched(movie, series, etc)
     * @param country specifies the origin of data to be fetched(example - you only want Indian movies, then you can specify the country)
     */
    @GET(Endpoints.SEARCH)
    suspend fun searchMovies(
        @Query("term") term: String,
        @Query("media") media: String = "movie",
        @Query("country") country: String = "au",
    ): MoviesResponseDto?
}