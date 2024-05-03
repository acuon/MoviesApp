package com.acuon.moviesapp.data.pref

class MoviesPreferences : Preferences() {
    var currentScreen by stringPref(MoviesPreferenceKeyEnums.CURRENT_SCREEN.value, "")
    var lastMovieId by longPref(MoviesPreferenceKeyEnums.CURRENT_MOVIE_ID.value)
}