package com.acuon.moviesapp.data.remote.dto


import com.acuon.moviesapp.domain.model.MovieItem
import com.google.gson.annotations.SerializedName

data class MoviesResponseDto(
    @SerializedName("resultCount")
    val resultCount: Int? = null,
    @SerializedName("results")
    val results: List<MoviesItemDTO?>? = null
)

data class MoviesItemDTO(
    @SerializedName("artistName")
    val artistName: String? = null,
    @SerializedName("artworkUrl100")
    val artworkUrl100: String? = null,
    @SerializedName("artworkUrl30")
    val artworkUrl30: String? = null,
    @SerializedName("artworkUrl60")
    val artworkUrl60: String? = null,
    @SerializedName("collectionArtistId")
    val collectionArtistId: Int? = null,
    @SerializedName("collectionArtistViewUrl")
    val collectionArtistViewUrl: String? = null,
    @SerializedName("collectionCensoredName")
    val collectionCensoredName: String? = null,
    @SerializedName("collectionExplicitness")
    val collectionExplicitness: String? = null,
    @SerializedName("collectionHdPrice")
    val collectionHdPrice: Double? = null,
    @SerializedName("collectionId")
    val collectionId: Int? = null,
    @SerializedName("collectionName")
    val collectionName: String? = null,
    @SerializedName("collectionPrice")
    val collectionPrice: Double? = null,
    @SerializedName("collectionViewUrl")
    val collectionViewUrl: String? = null,
    @SerializedName("contentAdvisoryRating")
    val contentAdvisoryRating: String? = null,
    @SerializedName("country")
    val country: String,
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("discCount")
    val discCount: Int? = null,
    @SerializedName("discNumber")
    val discNumber: Int? = null,
    @SerializedName("hasITunesExtras")
    val hasITunesExtras: Boolean? = null,
    @SerializedName("kind")
    val kind: String? = null,
    @SerializedName("longDescription")
    val longDescription: String? = null,
    @SerializedName("previewUrl")
    val previewUrl: String? = null,
    @SerializedName("primaryGenreName")
    val primaryGenreName: String? = null,
    @SerializedName("releaseDate")
    val releaseDate: String? = null,
    @SerializedName("shortDescription")
    val shortDescription: String? = null,
    @SerializedName("trackCensoredName")
    val trackCensoredName: String? = null,
    @SerializedName("trackCount")
    val trackCount: Int? = null,
    @SerializedName("trackExplicitness")
    val trackExplicitness: String? = null,
    @SerializedName("trackHdPrice")
    val trackHdPrice: Double? = null,
    @SerializedName("trackHdRentalPrice")
    val trackHdRentalPrice: Double? = null,
    @SerializedName("trackId")
    val trackId: Long? = null,
    @SerializedName("trackName")
    val trackName: String? = null,
    @SerializedName("trackNumber")
    val trackNumber: Int? = null,
    @SerializedName("trackPrice")
    val trackPrice: Double? = null,
    @SerializedName("trackRentalPrice")
    val trackRentalPrice: Double? = null,
    @SerializedName("trackTimeMillis")
    val trackTimeMillis: Long? = null,
    @SerializedName("trackViewUrl")
    val trackViewUrl: String? = null,
    @SerializedName("wrapperType")
    val wrapperType: String? = null
)

fun MoviesItemDTO.toMovieItem(): MovieItem {
    return MovieItem(
        trackId = trackId,
        name = trackName,
        poster = artworkUrl100,
        shortDescription = shortDescription,
        longDescription = longDescription,
        releaseDate = releaseDate,
        length = trackTimeMillis,
        contentAdvisoryRating = contentAdvisoryRating,
        trailerUrl = previewUrl,
        genre = primaryGenreName,
        price = trackHdPrice,
        currency = currency
    )
}