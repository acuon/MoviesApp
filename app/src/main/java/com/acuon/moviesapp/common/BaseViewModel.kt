package com.acuon.moviesapp.common

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acuon.moviesapp.data.pref.MoviesPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    private val mIsLoading = ObservableBoolean()
    protected val pref by lazy { MoviesPreferences() }

    fun getIsLoading(): ObservableBoolean {
        return mIsLoading
    }

    fun setIsLoading(isLoading: Boolean) {
        mIsLoading.set(isLoading)
    }

    fun execute(dispatcher: CoroutineContext = Dispatchers.Main, job: suspend () -> Unit) =
        viewModelScope.launch(dispatcher) {
            job.invoke()
        }

    fun ignoreCoroutineException(throwable: Throwable, callback: () -> Unit) {
        if (throwable.message?.contains("Standalone") != true)
            callback.invoke()
    }

    fun runDelayed(delayMilliSec: Long, job: suspend () -> Unit) =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                delay(delayMilliSec)
                withContext(Dispatchers.Main) {
                    job.invoke()
                }
            }
        }
}