package pl.dmardev172.movieexplorer

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pl.dmardev172.movieexplorer.di.networkModule
import pl.dmardev172.movieexplorer.di.repositoryModule
import pl.dmardev172.movieexplorer.di.viewModelModule

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieApp)
            modules(
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}