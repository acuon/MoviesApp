package com.acuon.moviesapp.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.acuon.moviesapp.utils.extensions.DateFormats
import com.acuon.moviesapp.utils.extensions.serverToPrettyDate
import com.acuon.moviesapp.utils.extensions.toMinutes
import kotlinx.parcelize.Parcelize

/**
 * Entity table for MoviesDatabase(Movies Cache)
 */
@Entity(tableName = "MoviesDatabase")
@Parcelize
data class MovieItem(
    @PrimaryKey
    @ColumnInfo(name = "trackId")
    var trackId: Long? = null,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "poster")
    var poster: String? = null,
    @ColumnInfo(name = "shortDescription")
    var shortDescription: String? = null,
    @ColumnInfo(name = "longDescription")
    var longDescription: String? = null,
    @ColumnInfo(name = "releaseDate")
    var releaseDate: String? = null,
    @ColumnInfo(name = "length")
    var length: Long? = null,
    @ColumnInfo(name = "contentAdvisoryRating")
    var contentAdvisoryRating: String? = null,
    @ColumnInfo(name = "trailerUrl")
    var trailerUrl: String? = null,
    @ColumnInfo(name = "genre")
    var genre: String? = null,
    @ColumnInfo(name = "price")
    var price: Double? = null,
    @ColumnInfo(name = "currency")
    var currency: String? = null,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable {

    val prettyDate: String
        get() = releaseDate.serverToPrettyDate(
            DateFormats.SERVER_DATE_FORMAT,
            DateFormats.PRETTY_DATE_FORMAT2
        )

    val prettyDateFull: String
        get() = releaseDate.serverToPrettyDate(
            DateFormats.SERVER_DATE_FORMAT,
            DateFormats.PRETTY_DATE_FORMAT
        )

    val lengthInMinutes: String
        get() = "${length?.toMinutes()}m"
}

/**
 * extension function to convert MovieItem object to FavoriteMovieItem
 */
fun MovieItem.toFavoriteMovieItem(): FavoriteMovieItem {
    return FavoriteMovieItem(
        trackId = trackId,
        name = name,
        poster = poster,
        shortDescription = shortDescription,
        longDescription = longDescription,
        releaseDate = releaseDate,
        length = length,
        contentAdvisoryRating = contentAdvisoryRating,
        trailerUrl = trailerUrl,
        genre = genre,
        price = price,
        currency = currency,
        isFavorite = isFavorite
    )
}