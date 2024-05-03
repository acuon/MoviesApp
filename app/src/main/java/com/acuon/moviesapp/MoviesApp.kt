package com.acuon.moviesapp

import android.app.Application
import com.acuon.moviesapp.data.pref.Preferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MoviesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Preferences.init(this)
    }
}