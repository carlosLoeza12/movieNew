package com.example.movienew.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movienew.application.AppConstants
import com.example.movienew.core.BaseViewHolder
import com.example.movienew.data.model.Movie
import com.example.movienew.databinding.MovieItemBinding

class MovieAdapter(
    private val movieList: List<Movie>,
    private val itemClickListener: OnMovieClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMovieClickListener {
        //Se crea una interfaz que contiene un metodo que al ser llamado tengo que pasarle una pelicula
        fun onMovieClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        //Es el encargado de crear e inflar la vista que esta asociada a un xml
        val itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MoviesViewHolder(itemBinding)
        itemBinding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf { //toma la posicion si es diferente de -1
                it != DiffUtil.DiffResult.NO_POSITION
            } ?: return@setOnClickListener
            itemClickListener.onMovieClick(movieList[position])
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
       //obtener cada posicion del recycler y ponerle la vista creada en el oncreateview holder
        when(holder){
            is MoviesViewHolder -> holder.bind(movieList[position])
        }
    }

    override fun getItemCount(): Int = movieList.size

    private inner class MoviesViewHolder(val binding: MovieItemBinding): BaseViewHolder<Movie>(binding.root){
        //Se crea el vieholder extendiendo de base viewholder, Se recibe la vista que se va utilizar y al base se le pone el tipo
        // que en este caso es movie
        override fun bind(itemView: Movie) {
            binding.imgMovie.load(AppConstants.BASE_IMAGE+itemView.poster_path)
        }

    }
}