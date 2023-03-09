package com.example.moviesearchapp.domain.retrofit

import com.example.moviesearchapp.domain.RemoteMovieDataSource
import com.example.moviesearchapp.domain.model.Movie

class RetrofitMovieDataSource(private val movieDbApi: MovieDbApi): RemoteMovieDataSource {


    override suspend fun search(query: String): Movie {
        val movieResponse = movieDbApi.search(query)
        val responseBody = movieResponse.body()
        if (movieResponse.isSuccessful && responseBody!=null) {
            return responseBody
        }
        throw Exception(movieResponse.message())
    }
}
