package com.example.moviesearchapp.domain

import com.example.moviesearchapp.domain.model.Movie

interface LocalMovieDataSource {

    suspend fun insert(movie: Movie)

    suspend fun getAll(): List<Movie>

    suspend fun getById(id:String): Movie
}
