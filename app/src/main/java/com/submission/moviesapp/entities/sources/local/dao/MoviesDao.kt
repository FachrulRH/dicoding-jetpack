package com.submission.moviesapp.entities.sources.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.submission.moviesapp.entities.Movies

@Dao
interface MoviesDao {
    @get:Query("SELECT * FROM Movie")
    val allMovies: LiveData<List<Movies>>

    @Query("SELECT * FROM Movie")
    fun allMoviesAsPaged(): DataSource.Factory<Int, Movies>

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getById(id: Int?): Movies?

    @Insert(onConflict = REPLACE)
    fun insert(movie: Movies): Long

    @Delete
    fun delete(movie: Movies)

    @Query("DELETE FROM Movie WHERE id = :id")
    fun deleteById(id: Long): Int

    @Update
    fun update(movie: Movies): Int
}