package pl.dmardev172.movieexplorer.di

import org.koin.dsl.module
import pl.dmardev172.movieexplorer.data.repository.MovieRepositoryImpl
import pl.dmardev172.movieexplorer.domain.repository.MovieRepository

val repositoryModule = module {
//    single<MovieRepository> {
//        MovieRepositoryImpl(get())
//    }
}