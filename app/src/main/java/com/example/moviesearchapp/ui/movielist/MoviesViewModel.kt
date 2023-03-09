package com.example.moviesearchapp.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesearchapp.domain.model.Movie
import com.example.moviesearchapp.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private val moviesLiveData = MutableLiveData<List<Movie>>()

    fun getMovieLiveData(): LiveData<List<Movie>> = moviesLiveData

    fun search(query:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = movieRepository.queryMovie(query)
            movieRepository.insertMovie(movie)
            moviesLiveData.postValue(movieRepository.listMovies())
        }
    }

    fun listAll() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = movieRepository.listMovies()
            moviesLiveData.postValue(movies)
        }
    }
}
