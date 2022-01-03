package com.submission.moviesapp.entities.sources.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.submission.moviesapp.entities.Movies
import com.submission.moviesapp.entities.TvShows
import com.submission.moviesapp.entities.sources.local.dao.MoviesDao
import com.submission.moviesapp.entities.sources.local.dao.TvShowsDao

@Database(entities = [Movies::class, TvShows::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase()  {

    abstract fun moviesDao(): MoviesDao
    abstract fun tvShowsDao(): TvShowsDao

    companion object {
        private const val DB_NAME = "moviesapp"

        private var dbInstance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (dbInstance == null) {
                dbInstance = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                    .allowMainThreadQueries()
                    .build()
            }
            return dbInstance as AppDatabase
        }
    }
}