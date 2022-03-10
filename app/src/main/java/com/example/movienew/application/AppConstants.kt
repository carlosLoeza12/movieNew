package com.example.movienew.application

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object AppConstants {
    //API
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "a28c4bc831b590dc669ef8a459fdbff7"
    const val BASE_IMAGE = "https://image.tmdb.org/t/p/w500/"

    // Create Logger
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    var okHttp = OkHttpClient.Builder().addInterceptor(logger)
}

