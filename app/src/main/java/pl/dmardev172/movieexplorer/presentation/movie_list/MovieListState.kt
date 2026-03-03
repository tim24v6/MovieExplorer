package pl.dmardev172.movieexplorer.presentation.movie_list

import pl.dmardev172.movieexplorer.domain.model.Movie

data class MovieListState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null
)
