package com.example.moviesearchapp.domain.room

import com.example.moviesearchapp.domain.LocalMovieDataSource
import com.example.moviesearchapp.domain.model.Movie

class RoomMovieDataSource(private val movieDao: MovieDao): LocalMovieDataSource {

    override suspend fun insert(movie: Movie) {
        movieDao.insert(movie)
    }

    override suspend fun getAll(): List<Movie> {
        return movieDao.getAll()
    }

    override suspend fun getById(id: String): Movie {
        return movieDao.getById(id)
    }


}
