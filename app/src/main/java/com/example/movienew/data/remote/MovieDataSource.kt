package com.example.movienew.data.remote
import com.example.movienew.application.AppConstants
import com.example.movienew.data.model.MovieList
import com.example.movienew.repository.WebService
import javax.inject.Inject

class MovieDataSource @Inject constructor(private val webService: WebService) {

    suspend fun getUpcomingMovies(): MovieList = webService.getUpcomingMovies(AppConstants.API_KEY)
    suspend fun getTopRatedMovies(): MovieList = webService.getTopRatedMovies(AppConstants.API_KEY)
    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.API_KEY)

}