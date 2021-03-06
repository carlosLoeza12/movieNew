package com.example.movienew.repository

import com.example.movienew.data.model.MovieList
import com.example.movienew.data.remote.MovieDataSource

class MovieRepositoryImp(private val dataSource: MovieDataSource): MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList {
         return dataSource.getUpcomingMovies()
    }

    override suspend fun getTopRatedMovies(): MovieList {
        return dataSource.getTopRatedMovies()
    }

    override suspend fun getPopularMovies(): MovieList {
        return dataSource.getPopularMovies()
    }

}