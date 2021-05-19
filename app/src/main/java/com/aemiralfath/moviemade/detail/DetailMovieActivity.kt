package com.aemiralfath.moviemade.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.aemiralfath.core.domain.model.Movie
import com.aemiralfath.moviemade.R
import com.aemiralfath.moviemade.databinding.ActivityDetailMovieBinding
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            supportActionBar?.title = detailMovie.title
            binding.content.tvDetailDescription.text = detailMovie.overview

            Glide.with(this@DetailMovieActivity)
                .load("https://image.tmdb.org/t/p/w780${detailMovie.backdropPath}")
                .into(binding.ivDetailImage)

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)

            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }

        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white)
            )
        }
    }
}