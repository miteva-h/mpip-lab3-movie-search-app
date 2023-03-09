package com.example.moviesearchapp.ui.movielist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesearchapp.domain.repository.MovieRepository
import com.example.moviesearchapp.domain.retrofit.MovieDbApiProvider
import com.example.moviesearchapp.domain.retrofit.RetrofitMovieDataSource
import com.example.moviesearchapp.domain.room.AppDatabase
import com.example.moviesearchapp.domain.room.RoomMovieDataSource

class MoviesViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java)
            .newInstance(MovieRepository(
                RetrofitMovieDataSource(MovieDbApiProvider.getMovieDbApi()),
                RoomMovieDataSource(AppDatabase.getDatabase(context).movieDao())
            ))
    }
}
