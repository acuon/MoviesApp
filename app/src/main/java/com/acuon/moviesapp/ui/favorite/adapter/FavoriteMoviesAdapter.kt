package com.acuon.moviesapp.ui.favorite.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acuon.moviesapp.databinding.ItemLayoutMoviesBinding
import com.acuon.moviesapp.domain.model.MovieItem
import com.acuon.moviesapp.utils.extensions.executeWithAction
import com.acuon.moviesapp.utils.extensions.inflater
import javax.inject.Inject

class FavoriteMoviesAdapter @Inject constructor() : RecyclerView.Adapter<FavoriteMoviesAdapter.ViewHolder>() {

    var list: List<MovieItem?>? = null
        set(value) {
            field = value
            notifyItemRangeChanged(0, value?.size ?: 0)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemLayoutMoviesBinding.inflate(parent.inflater(), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, list?.get(position))
    }

    private var onMovieClick: ((Int, MovieItem?) -> Unit)? = null
    private var onFavoriteClick: ((Int, MovieItem?) -> Unit)? = null

    fun setOnMovieClickListener(listener: (Int, MovieItem?) -> Unit) {
        onMovieClick = listener
    }

    fun setOnFavoriteClickListener(listener: (Int, MovieItem?) -> Unit) {
        onFavoriteClick = listener
    }

    inner class ViewHolder(private val binding: ItemLayoutMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, movie: MovieItem?) {
            binding.executeWithAction { item = movie }
            binding.root.setOnClickListener {
                onMovieClick?.invoke(position, movie)
            }
            binding.layoutFavorite.setOnClickListener {
                movie?.isFavorite = !(movie?.isFavorite ?: false)
                notifyItemRemoved(position)
                onFavoriteClick?.invoke(position, movie)
            }
        }
    }
}