package com.submission.moviesapp.di

import android.content.Context
import com.submission.moviesapp.entities.sources.Repository
import com.submission.moviesapp.entities.sources.local.LocalDataSource
import com.submission.moviesapp.entities.sources.remote.RemoteDataSource
import com.submission.moviesapp.ui.detail.DetailMoviesViewModel
import com.submission.moviesapp.ui.detail.DetailTvShowsViewModel
import com.submission.moviesapp.ui.favorite.FavoriteViewModel
import com.submission.moviesapp.ui.home.HomeViewModel
import com.submission.moviesapp.ui.movies.MoviesViewModel
import com.submission.moviesapp.ui.tvshows.TvShowsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { MoviesViewModel(get()) }
    viewModel { TvShowsViewModel(get()) }
    viewModel { DetailMoviesViewModel(get()) }
    viewModel { DetailTvShowsViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}

val repositoryModule = module {
    fun provideRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource) : Repository {
        return Repository.getInstance(remoteDataSource, localDataSource)
    }
    single { provideRepository(get(), get()) }
}

val remoteDataModule = module {
    fun provideRemoteDataSource() : RemoteDataSource {
        return RemoteDataSource.getInstance()
    }
    single { provideRemoteDataSource() }
}

val localDataModule = module{
    fun provideLocalDataSource(context: Context) : LocalDataSource {
        return LocalDataSource.getInstance(context)
    }
    single { provideLocalDataSource(get()) }
}
