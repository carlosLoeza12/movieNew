package com.example.movienew.ui.moviedetail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.movienew.R
import com.example.movienew.aplication.AppConstants
import com.example.movienew.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)

        binding.txtTitle.text = args.title
        binding.txtOverview.text = args.overView
        binding.imgMovie.load(AppConstants.BASE_IMAGE+args.posterImageUrl)
        binding.imgBackground.load(AppConstants.BASE_IMAGE+args.backgroundImageUrl)
        binding.txtLanguage.text = "${getString(R.string.movie_language)} ${args.language}"
        binding.txtRating.text = "${args.voteAverage} (${args.voteCount} ${getString(R.string.movie_review)})"
        binding.txtRelease.text = "${getString(R.string.movie_release)} ${args.releaseDate}"

    }
}