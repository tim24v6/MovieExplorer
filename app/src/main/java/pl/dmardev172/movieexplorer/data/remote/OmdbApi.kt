package pl.dmardev172.movieexplorer.data.remote

import pl.dmardev172.movieexplorer.data.remote.dto.MovieDetailsDto
import pl.dmardev172.movieexplorer.data.remote.dto.MovieSearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {

    @GET("/")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("s") query: String
    ): MovieSearchResponseDto

    @GET("/")
    suspend fun getMovieDetails(
        @Query("apiKey") apiKey: String,
        @Query("i") imdbId: String
    ): MovieDetailsDto
}