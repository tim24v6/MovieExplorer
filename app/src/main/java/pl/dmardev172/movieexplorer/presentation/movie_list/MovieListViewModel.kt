package pl.dmardev172.movieexplorer.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.dmardev172.movieexplorer.domain.repository.MovieRepository

class MovieListViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _state = MutableStateFlow(MovieListState())
    val state = _state.asStateFlow()

    suspend fun search(query: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
        }

        try {
            val movies = repository.searchMovies(query)
            _state.update {
                it.copy(
                    isLoading = false,
                    movies = movies
                )
            }
        } catch (e: Exception) {
            _state.update {
                it.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}