package com.example.movienew.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.movienew.R
import com.example.movienew.core.Resource
import com.example.movienew.data.model.Movie
import com.example.movienew.data.remote.MovieDataSource
import com.example.movienew.databinding.FragmentMovieBinding
import com.example.movienew.presentation.MovieViewModel
import com.example.movienew.presentation.MovieViewModelFactory
import com.example.movienew.repository.MovieRepositoryImp
import com.example.movienew.repository.RetrofitClient
import com.example.movienew.ui.adapters.MovieAdapter
import com.example.movienew.ui.adapters.concat.PopularConcatAdapter
import com.example.movienew.ui.adapters.concat.TopRatedConcatAdapter
import com.example.movienew.ui.adapters.concat.UpcomingConcatAdapter

class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var concatAdapter: ConcatAdapter
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(MovieRepositoryImp(MovieDataSource(RetrofitClient.WebService)))
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {
                    Log.e("aa", "loading")
                    binding.rtvProgress.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.rtvProgress.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(0, UpcomingConcatAdapter(MovieAdapter(it.data.first.results, this@MovieFragment)))
                        addAdapter(1, TopRatedConcatAdapter(MovieAdapter(it.data.second.results, this@MovieFragment)))
                        addAdapter(2, PopularConcatAdapter(MovieAdapter(it.data.third.results, this@MovieFragment)))
                    }
                    binding.recyclerMovies.adapter = concatAdapter
                    Log.e("aa", "${it.data}")
                }
                is Resource.Fail -> {
                    binding.rtvProgress.visibility = View.GONE
                    Log.e("error", "${it.exception}")
                }
            }
        })
    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average,
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )
        findNavController().navigate(action)
    }

}