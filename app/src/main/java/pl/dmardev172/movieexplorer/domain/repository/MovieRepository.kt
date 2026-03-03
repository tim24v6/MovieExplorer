package pl.dmardev172.movieexplorer.domain.repository

import pl.dmardev172.movieexplorer.domain.model.Movie

interface MovieRepository {
    suspend fun searchMovies(query: String): List<Movie>
}