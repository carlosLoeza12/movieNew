package com.example.movienew.ui.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movienew.core.BaseConcatHolder
import com.example.movienew.databinding.PopularRowBinding
import com.example.movienew.databinding.UpcomingRowBinding
import com.example.movienew.ui.adapters.MovieAdapter

class UpcomingConcatAdapter (private val movieAdapter: MovieAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = UpcomingRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bind(movieAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val bindig: UpcomingRowBinding): BaseConcatHolder<MovieAdapter>(bindig.root){
        override fun bind(adapter: MovieAdapter) {
            bindig.recyclerUpcoming.adapter = adapter
        }
    }
}