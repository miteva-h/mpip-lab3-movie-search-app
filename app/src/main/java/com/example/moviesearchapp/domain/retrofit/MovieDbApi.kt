package com.example.moviesearchapp.domain.retrofit

import com.example.moviesearchapp.domain.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApi {
    @GET("/")
    suspend fun search(@Query("t") t:String): Response<Movie>

}