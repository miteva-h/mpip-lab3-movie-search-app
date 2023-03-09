package com.example.moviesearchapp.domain.repository

import com.example.moviesearchapp.domain.LocalMovieDataSource
import com.example.moviesearchapp.domain.RemoteMovieDataSource
import com.example.moviesearchapp.domain.model.Movie

class MovieRepository(
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val localMovieDataSource: LocalMovieDataSource
) {

    suspend fun queryMovie(query: String): Movie {
        return remoteMovieDataSource.search(query).apply { localMovieDataSource.insert(this) }
    }

    suspend fun insertMovie(movie: Movie){
        return localMovieDataSource.insert(movie)
    }

    suspend fun listMovies(): List<Movie> {
        return localMovieDataSource.getAll()
    }

    suspend fun listById(id: String):Movie {
        return localMovieDataSource.getById(id)
    }
}
