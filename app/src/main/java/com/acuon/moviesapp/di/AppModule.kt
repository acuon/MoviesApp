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

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun providesMoviesRepository(
        apiService: MoviesApi,
        moviesDao: MoviesDao,
    ): IHomeRepository {
        return HomeRepositoryImpl(apiService, moviesDao)
    }

    @Provides
    @Singleton
    fun providesFavoriteMoviesRepository(
        favoriteMoviesDao: FavoriteMoviesDao
    ): IFavoriteMovieRepository {
        return FavoriteMovieRepositoryImpl(favoriteMoviesDao)
    }

    @Provides
    @Singleton
    fun provideMoviesDao(database: MoviesDatabase): MoviesDao {
        return database.getMoviesDao()
    }

    @Provides
    @Singleton
    fun provideCachedMoviesDao(database: MoviesDatabase): FavoriteMoviesDao {
        return database.getFavoriteMoviesDao()
    }

    @Provides
    @Singleton
    fun provideMoviesDatabase(application: Application): MoviesDatabase {
        return Room.databaseBuilder(
            application,
            MoviesDatabase::class.java,
            "MoviesDatabase"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

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

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}