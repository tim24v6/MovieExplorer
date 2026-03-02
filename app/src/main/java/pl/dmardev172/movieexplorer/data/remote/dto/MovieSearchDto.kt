package pl.dmardev172.movieexplorer.data.remote.dto

data class MovieSearchDto(
    val imdbID: String,
    val Title: String,
    val Year: String,
    val Type: String,
//    val genre: String,
//    val director: String,
//    val writer: String,
    val Poster: String
)


/*    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val vote_average: Double*/
