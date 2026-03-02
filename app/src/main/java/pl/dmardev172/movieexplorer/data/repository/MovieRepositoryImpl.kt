package pl.dmardev172.movieexplorer.data.repository

import pl.dmardev172.movieexplorer.data.mapper.toDomain
import pl.dmardev172.movieexplorer.data.remote.OmdbApi
import pl.dmardev172.movieexplorer.domain.model.Movie
import pl.dmardev172.movieexplorer.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val api: OmdbApi,
    private val apiKey: String
) : MovieRepository {

    override suspend fun searchMovies(query:String): List<Movie> {
        val response = api.searchMovies(apiKey, query)
        val searchResults = response.Search ?: return emptyList()

        return searchResults.take(6).map { movieDto ->
            val details = api.getMovieDetails(apiKey, movieDto.imdbID)
            movieDto.toDomain(details.imdbRating)
        }
    }
}