package com.example.moviesearchapp.domain.room

import androidx.room.*
import com.example.moviesearchapp.domain.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<Movie>

    @Query("SELECT * FROM movie WHERE imdbID=:id LIMIT 1")
    suspend fun getById(id: String): Movie

}
