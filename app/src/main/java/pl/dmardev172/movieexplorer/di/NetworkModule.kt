package pl.dmardev172.movieexplorer.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import pl.dmardev172.movieexplorer.data.remote.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single<MovieApi> {
        get<Retrofit>().create(MovieApi::class.java)
    }
}