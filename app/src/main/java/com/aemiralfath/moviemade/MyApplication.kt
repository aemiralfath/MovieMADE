package com.aemiralfath.moviemade

import android.app.Application
import com.aemiralfath.core.di.databaseModule
import com.aemiralfath.core.di.networkModule
import com.aemiralfath.core.di.repositoryModule
import com.aemiralfath.moviemade.di.useCaseModule
import com.aemiralfath.moviemade.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}