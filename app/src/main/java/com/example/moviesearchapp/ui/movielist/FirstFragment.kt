package com.example.moviesearchapp.ui.movielist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.moviesearchapp.R
import com.example.moviesearchapp.adapters.MovieAdapter
import com.example.moviesearchapp.databinding.FragmentMoviesBinding
import com.example.moviesearchapp.domain.model.Movie
import com.example.moviesearchapp.ui.moviedetails.SecondFragment
import com.example.moviesearchapp.ui.moviedetails.MovieDetailsViewModel
import com.google.android.material.snackbar.Snackbar

class FirstFragment : Fragment(R.layout.fragment_movies) {

    private var _binding: FragmentMoviesBinding? = null

    private val binding get() = _binding!!

    private lateinit var moviesViewModel:MoviesViewModel

    private val movieDetailsViewModel: MovieDetailsViewModel by activityViewModels{MoviesViewModelFactory(requireContext())}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMoviesBinding.bind(view)

        val viewModelFactory = MoviesViewModelFactory(requireContext())
        moviesViewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]

        val clicker = object : MovieAdapter.OnClickListener {
            override fun onClickItem(movieId: String) {
                movieDetailsViewModel.listAllDetails(movieId)
                parentFragmentManager.commit {
                    replace(R.id.fragment_container_view, SecondFragment())
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
        }

        var adapter: MovieAdapter = MovieAdapter(ArrayList<Movie>(), clicker)
        binding.list.adapter = adapter

        moviesViewModel.getMovieLiveData().observe(viewLifecycleOwner) {
            adapter.updateMovies(it)
        }

        binding.button.setOnClickListener{
            val query = binding.editQuery.text.toString()
            if (query.isEmpty()) {
                Snackbar.make(view,R.string.please_enter_query, Snackbar.LENGTH_LONG).show()
            } else {
                moviesViewModel.search(query)
            }

        }

        moviesViewModel.listAll()

    }



}
