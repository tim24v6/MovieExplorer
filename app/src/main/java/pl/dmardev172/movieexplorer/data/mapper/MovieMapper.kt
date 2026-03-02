package pl.dmardev172.movieexplorer.data.mapper

import pl.dmardev172.movieexplorer.data.remote.dto.MovieSearchDto
import pl.dmardev172.movieexplorer.domain.model.Movie

/*
fun MovieDto.toDomain(): Movie {
    return Movie(
        title = title,
        genre = genre,
        overview = overview,
        director = director,
        writer = writer,
        posterUrl = "https://image.tmdb.org/t/p/w500$poster_path"
    )
}*/

fun MovieSearchDto.toDomain(rating: String): Movie {
    return Movie(
        imdbId = imdbID,
        title = Title,
        year = Year,
        type = Type,
        poster = Poster,
        rating = rating
    )
}