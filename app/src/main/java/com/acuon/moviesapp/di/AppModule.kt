package com.acuon.moviesapp.di

import android.app.Application
import androidx.room.Room
import com.acuon.moviesapp.common.Constants
import com.acuon.moviesapp.data.local.FavoriteMoviesDao
import com.acuon.moviesapp.data.local.MoviesDao
import com.acuon.moviesapp.data.local.MoviesDatabase
import com.acuon.moviesapp.data.remote.MoviesApi
import com.acuon.moviesapp.data.repository.FavoriteMovieRepositoryImpl
import com.acuon.moviesapp.domain.repository.IHomeRepository
import com.acuon.moviesapp.data.repository.HomeRepositoryImpl
import com.acuon.moviesapp.domain.repository.IFavoriteMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Dagger Hilt module that provides various dependencies for the application
 * This module includes providers for Retrofit, OkHttpClient, Room database, API services and repository implementations
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * provides the MoviesApi service using Retrofit
     */
    @Provides
    @Singleton
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    /**
     * provides the implementation of the Home repository
     */
    @Provides
    @Singleton
    fun providesMoviesRepository(
        apiService: MoviesApi,
        moviesDao: MoviesDao,
    ): IHomeRepository {
        return HomeRepositoryImpl(apiService, moviesDao)
    }

    /**
     * provides the implementation of the Favorite Movie repository
     */
    @Provides
    @Singleton
    fun providesFavoriteMoviesRepository(
        favoriteMoviesDao: FavoriteMoviesDao
    ): IFavoriteMovieRepository {
        return FavoriteMovieRepositoryImpl(favoriteMoviesDao)
    }

    /**
     * provides the MoviesDao for accessing regular movies in the database
     */
    @Provides
    @Singleton
    fun provideMoviesDao(database: MoviesDatabase): MoviesDao {
        return database.getMoviesDao()
    }

    /**
     * provides the FavoriteMoviesDao for accessing favorite movies in the database
     */
    @Provides
    @Singleton
    fun provideCachedMoviesDao(database: MoviesDatabase): FavoriteMoviesDao {
        return database.getFavoriteMoviesDao()
    }

    /**
     * provides the MoviesDatabase instance using Room
     */
    @Provides
    @Singleton
    fun provideMoviesDatabase(application: Application): MoviesDatabase {
        return Room.databaseBuilder(
            application,
            MoviesDatabase::class.java,
            "MoviesDatabase"
        ).fallbackToDestructiveMigration().build()
    }

    /**
     * provides the Retrofit instance with a base URL and Gson converter factory
     */
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    /**
     * provides the OkHttpClient instance with a logging interceptor
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
        return okHttpClient.build()
    }

    /**
     * provides the HttpLoggingInterceptor instance for logging network requests and responses
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}
