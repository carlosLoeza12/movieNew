package com.example.movienew.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.movienew.R
import com.example.movienew.core.Resource
import com.example.movienew.core.extensions.loadDialog
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

        toolbarConfiguration()
        getMovies()
        //show item toolbar
        setHasOptionsMenu(true);


    }

    private fun toolbarConfiguration(){
        val toolbar = binding.toolbar
        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
            (activity as AppCompatActivity).setTitle(R.string.movie)
        }

        val window = this.activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window?.statusBarColor = this.resources.getColor(R.color.red)

        concatAdapter = ConcatAdapter()
    }

    private fun getMovies(){
        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {
                    Log.e("aa", "loading")
                    binding.progress.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(0, UpcomingConcatAdapter(MovieAdapter(it.data.first.results, this@MovieFragment)))
                        addAdapter(1, TopRatedConcatAdapter(MovieAdapter(it.data.second.results, this@MovieFragment)))
                        addAdapter(2, PopularConcatAdapter(MovieAdapter(it.data.third.results, this@MovieFragment)))
                    }
                    binding.recyclerMovies.adapter = concatAdapter
                    Log.e("aa", "${it.data}")
                }
                is Resource.Fail -> {
                    binding.progress.visibility = View.GONE
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.itemClose ->{
                requireContext().loadDialog()
            }
            R.id.itemSettings -> {
                Log.e("aaa","settings")
            }

        }
        return super.onOptionsItemSelected(item)
    }

}