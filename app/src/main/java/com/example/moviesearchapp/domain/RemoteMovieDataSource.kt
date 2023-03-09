package com.example.moviesearchapp.domain

import com.example.moviesearchapp.domain.model.Movie

interface RemoteMovieDataSource {
    suspend fun search(query:String): Movie
}