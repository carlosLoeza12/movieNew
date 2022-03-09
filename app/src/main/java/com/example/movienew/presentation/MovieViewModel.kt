package com.example.movienew.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.movienew.core.Resource
import com.example.movienew.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val repository: MovieRepository): ViewModel(){

    fun fetchMainScreenMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(
                Resource.Success(
                    Triple(
                        repository.getUpcomingMovies(),
                        repository.getTopRatedMovies(),
                        repository.getPopularMovies()
                    )
                )
            )
        } catch (e: Exception) {
            Resource.Fail(e)
        }

    }
}

class MovieViewModelFactory(private val repo: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }

}