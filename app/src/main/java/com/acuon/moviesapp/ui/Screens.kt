package com.acuon.moviesapp.ui

/**
 * Enum class defining the screens of the application.
 *
 * @property value The unique identifier of the screen.
 */
enum class Screens(val value: String) {
    /**
     * Represents the home screen
     */
    HOME("home_screen"),

    /**
     * Represents the movie detail screen
     */
    MOVIE_DETAIL("movie_detail_screen"),

    /**
     * Represents the favorites screen
     */
    FAVORITES("favorites_screen")
}