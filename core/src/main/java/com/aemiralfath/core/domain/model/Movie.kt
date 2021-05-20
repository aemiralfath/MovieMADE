package com.aemiralfath.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieId: Int,
    val title: String,
    val overview: String,
    val originalLanguage: String,
    val backdropPath: String,
    val releaseDate: String,
    val popularity: Double,
    val adult: Boolean,
    val voteCount: Int,
    val isFavorite: Boolean
) : Parcelable