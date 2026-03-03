package pl.dmardev172.movieexplorer.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.dmardev172.movieexplorer.presentation.movie_list.MovieListViewModel

val viewModelModule = module {
    viewModel { MovieListViewModel(get()) }
}