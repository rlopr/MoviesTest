package com.example.rubylopez.movietest.common.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesResults(val vote_count: Number?, val id: Number?, val video: Boolean?, val vote_average: Number?, val title: String?, val popularity: Number?, val poster_path: String?, val original_language: String?, val original_title: String?, val genre_ids: List<Number>?, val backdrop_path: String?, val adult: Boolean?, val overview: String?, val release_date: String?) : Parcelable
