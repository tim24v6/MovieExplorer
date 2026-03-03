package pl.dmardev172.movieexplorer.fake

import pl.dmardev172.movieexplorer.domain.model.Movie
import pl.dmardev172.movieexplorer.domain.repository.MovieRepository
import java.lang.RuntimeException

class FakeMoveRepository : MovieRepository {

    var shouldThrowError = false

    override suspend fun searchMovies(query: String): List<Movie> {
        if (shouldThrowError) {
            throw RuntimeException("Network Error")
        }

        return listOf(
            Movie(
                imdbId = "1",
                title = "Batman Begins",
                year = "2005",
                type = "movie",
                poster = "url",
                rating = "8.2"
            )
        )
    }
}