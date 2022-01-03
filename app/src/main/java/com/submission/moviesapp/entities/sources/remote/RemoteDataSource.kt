package com.submission.moviesapp.entities.sources.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.submission.moviesapp.entities.Movies
import com.submission.moviesapp.entities.TrendingMovies
import com.submission.moviesapp.entities.TrendingTvShows
import com.submission.moviesapp.entities.TvShows
import com.submission.moviesapp.entities.sources.remote.api.ApiConfig
import com.submission.moviesapp.entities.sources.remote.response.*
import com.submission.moviesapp.utils.EspresssoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }

        private val TAG = RemoteDataSource::class.java.simpleName
    }

    fun getMoviesTrending(page: Int) : LiveData<List<TrendingMovies>> {
        val trendingResponse = MutableLiveData<List<TrendingMovies>>()
        val client = ApiConfig.getApiService().getAllMoviesTrending(page)

        EspresssoIdlingResource.increment()
        EspresssoIdlingResource.increment()

        client.enqueue(object : Callback<TrendingMoviesResponse> {
            override fun onResponse(call: Call<TrendingMoviesResponse>, moviesResponse: Response<TrendingMoviesResponse>) {
                if (moviesResponse.isSuccessful) {
                    moviesResponse.body()?.let { trendingResponse.postValue(it.results) }
                    EspresssoIdlingResource.decrement()
                    EspresssoIdlingResource.decrement()
                } else {
                    Log.e(TAG, moviesResponse.message())
                }
            }
            override fun onFailure(call: Call<TrendingMoviesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        Log.d(TAG, "getAllTrending $trendingResponse")
        return trendingResponse
    }

    fun getTvShowsTrending(page: Int) : LiveData<List<TrendingTvShows>> {
        val trendingResponse = MutableLiveData<List<TrendingTvShows>>()
        val client = ApiConfig.getApiService().getAllTvShowsTrending(page)

        EspresssoIdlingResource.increment()
        EspresssoIdlingResource.increment()

        client.enqueue(object : Callback<TrendingTvShowsResponse> {
            override fun onResponse(call: Call<TrendingTvShowsResponse>, tvShowsResponse: Response<TrendingTvShowsResponse>) {
                if (tvShowsResponse.isSuccessful) {
                    tvShowsResponse.body()?.let { trendingResponse.postValue(it.results) }
                    EspresssoIdlingResource.decrement()
                    EspresssoIdlingResource.decrement()
                } else {
                    Log.e(TAG, tvShowsResponse.message())
                }
            }
            override fun onFailure(call: Call<TrendingTvShowsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        Log.d(TAG, "getAllTrending $trendingResponse")
        return trendingResponse
    }

    fun getAllMovies(page: Int) : LiveData<List<Movies>> {
        val movies  = MutableLiveData<List<Movies>>()
        val client = ApiConfig.getApiService().getAllMovies(page)

        EspresssoIdlingResource.increment()
        EspresssoIdlingResource.increment()

        client.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { movies.postValue(it.results) }
                    EspresssoIdlingResource.decrement()
                    EspresssoIdlingResource.decrement()
                } else {
                    Log.e(TAG, response.message())
                }
            }
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return movies
    }

    fun getAllTvShows(page: Int) : LiveData<List<TvShows>> {
        val tvShows = MutableLiveData<List<TvShows>>()
        val client = ApiConfig.getApiService().getAllTvShows(page)

        EspresssoIdlingResource.increment()
        EspresssoIdlingResource.increment()

        client.enqueue(object : Callback<TvShowsResponse> {
            override fun onResponse(call: Call<TvShowsResponse>, response: Response<TvShowsResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { tvShows.postValue(it.results) }
                    EspresssoIdlingResource.decrement()
                    EspresssoIdlingResource.decrement()
                } else {
                    Log.e(TAG, response.message())
                }
            }
            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return tvShows
    }

    fun getMovieById(moviesId: Int) : LiveData<Movies> {
        val detailMoviesResponse = MutableLiveData<Movies>()
        val client = ApiConfig.getApiService().getMoviesDetail(moviesId)

        EspresssoIdlingResource.increment()
        EspresssoIdlingResource.increment()
        EspresssoIdlingResource.increment()

        client.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful) {
                    response.body()?.let { detailMoviesResponse.postValue(it) }
                    EspresssoIdlingResource.decrement()
                    EspresssoIdlingResource.decrement()
                    EspresssoIdlingResource.decrement()
                } else {
                    Log.e(TAG, response.message())
                }
            }
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        Log.d(TAG, "getMoviesById: $detailMoviesResponse")
        return detailMoviesResponse
    }

    fun getTvShowById(tvShowsId: Int) : LiveData<TvShows> {
        val detailTvShows = MutableLiveData<TvShows>()
        val client = ApiConfig.getApiService().getTvShowsDetail(tvShowsId)

        EspresssoIdlingResource.increment()
        EspresssoIdlingResource.increment()
        EspresssoIdlingResource.increment()

        client.enqueue(object : Callback<TvShows> {
            override fun onResponse(call: Call<TvShows>, response: Response<TvShows>) {
                if (response.isSuccessful) {
                    response.body()?.let { detailTvShows.postValue(it) }
                    EspresssoIdlingResource.decrement()
                    EspresssoIdlingResource.decrement()
                    EspresssoIdlingResource.decrement()
                } else {
                    Log.e(TAG, response.message())
                }
            }

            override fun onFailure(call: Call<TvShows>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        Log.d(TAG, "getTvShowsById: $detailTvShows")
        return detailTvShows
    }
}