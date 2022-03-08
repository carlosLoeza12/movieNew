package com.example.movienew.repository

import com.example.movienew.aplication.AppConstants
import com.example.movienew.data.model.MovieList
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/popular")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/top_rated")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieList
}

object RetrofitClient{

    val WebService by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(AppConstants.okHttp.build())
            .build().create(WebService::class.java)
    }
}