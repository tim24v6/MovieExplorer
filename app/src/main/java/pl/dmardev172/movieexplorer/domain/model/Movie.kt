package pl.dmardev172.movieexplorer.domain.model

data class Movie(
    val imdbId: String,
    val title: String,
    val year: String,
    val type: String,
    val poster: String,
    val rating: String
)