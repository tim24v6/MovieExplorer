package pl.dmardev172.movieexplorer.di

import org.koin.dsl.module
import pl.dmardev172.movieexplorer.data.remote.OmdbApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
//            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single<OmdbApi> {
        get<Retrofit>().create(OmdbApi::class.java)
    }
}