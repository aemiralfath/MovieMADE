package com.aemiralfath.moviemade.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import com.aemiralfath.core.domain.model.Movie
import com.aemiralfath.moviemade.R
import com.aemiralfath.moviemade.databinding.ActivityDetailMovieBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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

            with(binding) {
                content.tvOverview.text = detailMovie.overview
                content.tvMovieLanguage.text = detailMovie.originalLanguage
                content.tvMoviePopularity.text = detailMovie.popularity.toString()
                content.tvMovieVote.text = detailMovie.voteCount.toString()
                content.tvMovieAdult.text =
                    if (detailMovie.adult) resources.getString(R.string.yes) else resources.getString(
                        R.string.no
                    )

                Glide.with(this@DetailMovieActivity)
                    .load("https://image.tmdb.org/t/p/w780${detailMovie.backdropPath}")
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivDetailImage)

                var statusFavorite = detailMovie.isFavorite
                setStatusFavorite(statusFavorite)

                fab.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailMovieViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }

                content.btnMovieShare.setOnClickListener {
                    ShareCompat.IntentBuilder
                        .from(this@DetailMovieActivity)
                        .setType("text/plain")
                        .setChooserTitle(R.string.share_movie)
                        .setText(resources.getString(R.string.share_text, detailMovie.title))
                        .startChooser()
                }
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