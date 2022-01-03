package com.submission.moviesapp.entities.sources.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.submission.moviesapp.entities.TvShows

@Dao
interface TvShowsDao {
    @get:Query("SELECT * FROM TvShow")
    val allTvShows: LiveData<List<TvShows>>

    @Query("SELECT * FROM TvShow")
    fun allTvShowsAsPaged(): DataSource.Factory<Int, TvShows>

    @Query("SELECT * FROM TvShow WHERE id = :id")
    fun getById(id: Int?): TvShows?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tvShow: TvShows)

    @Delete
    fun delete(tvShow: TvShows)
}