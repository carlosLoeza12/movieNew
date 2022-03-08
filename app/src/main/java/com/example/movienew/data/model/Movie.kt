package com.example.movienew.data.model
import com.google.gson.annotations.SerializedName

data class Movie(val id: Int,
                 @SerializedName("adult")
                 val adult: Boolean,
                 val backdrop_path: String,
                 val genre_ids: List<Int> = listOf(),
                 val original_language: String,
                 val original_title: String,
                 val overview: String,
                 val popularity: Float,
                 val poster_path: String,
                 val release_date: String,
                 val title: String,
                 val video: Boolean,
                 val vote_average: Float,
                 val vote_count: Int)

data class MovieList(val results: List<Movie> = listOf())
