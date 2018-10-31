package com.example.rubylopez.movietest.common.models

data class MoviesResponse(val results: List<MoviesResults>?, val page: Number?, val total_results: Number?, val dates: Dates?, val total_pages: Number?)


